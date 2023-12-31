import { useQuery } from '@tanstack/react-query';
import { getVolNoticeList, getVolNoticeListByLatLng } from 'apis/api/volunteer';
import React, { useState, useEffect } from 'react';
import { Map, MapMarker, Circle } from 'react-kakao-maps-sdk';
import styles from './SearchVolunteer.module.scss';
import SearchVolunteerMark from 'components/Volunteer/Search/SearchVolunteerMark';
import DetailModal from './DetailModal';

import VolunteerDetail from './VolunteerDetail';
export default function SearchVolunteer() {
  const [modal, setModal] = useState(false);
  const [modalData, setModalData] = useState();
  const [selectedMarker, setSeleteMarker] = useState();
  const [state, setState] = useState({
    center: {
      lat: 0,
      lng: 0,
    },
  });

  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', state],
    queryFn: () => getVolNoticeListByLatLng(state.center.lat.toString(), state.center.lng.toString()),
  });

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        setState(prev => ({
          ...prev,
          center: {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          },
          isLoading: false,
        }));
      });
    } else {
      setState(prev => ({
        ...prev,
        errMsg: '현재 위치 정보를 받아올 수 없습니다',
        isLoading: false,
      }));
    }
  }, []);

  // Console
  //

  const markToPage = x => {
    setModalData(x);
    if (x !== undefined) {
      setModal(true);
    }
  };

  const closeModal = () => {
    setModal(false);
  };

  if (isLoading) return;

  return (
    <div>
      <div className={styles.title}>봉사 신청 하기</div>
      <Map
        className={styles.map}
        center={state.center}
        level={10}
        onClick={(_t, mouseEvent) =>
          setState({
            center: {
              lat: mouseEvent.latLng.getLat(),
              lng: mouseEvent.latLng.getLng(),
            },
          })
        }
      >
        <Circle
          center={state.center}
          radius={30000}
          strokeWeight={1}
          strokeColor="#ffd80b"
          strokeOpacity={0.1}
          strokeStyle="solid"
          fillColor="#ffd80b"
          fillOpacity={0.2}
        />
        {!state.isLoading && (
          <MapMarker
            position={state.center}
            image={{
              src: 'https://i.ibb.co/z42FXX4/002-2.png',
              size: {
                width: 34,
                height: 36,
              },
            }}
          ></MapMarker>
        )}
        {data
          ? data.map((item, index) => (
              <SearchVolunteerMark
                latitude={item.latitude}
                longitude={item.longitude}
                shelterId={item.shelterId}
                name={item.name}
                key={index}
                modalIndex={index}
                markToPage={markToPage}
                onClick={() => setSeleteMarker(index)}
                isClicked={selectedMarker === index}
                onClose={() => setSeleteMarker(999)}
              />
            ))
          : null}
      </Map>
      {modalData ? (
        <DetailModal open={modal} close={closeModal} title={modalData.name}>
          <VolunteerDetail items={modalData} />
        </DetailModal>
      ) : (
        <DetailModal open={modal} close={closeModal}>
          <VolunteerDetail items={modalData} />
        </DetailModal>
      )}
    </div>
  );
}
