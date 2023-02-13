import { useQuery } from '@tanstack/react-query';
import { getVolNoticeList } from 'apis/api/volunteer';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import { getShelterDetail } from 'apis/api/shelter';

import React, { useState, useEffect } from 'react';
import { Map, MapMarker, Circle } from 'react-kakao-maps-sdk';
import styles from './SearchVolunteer.module.scss';
export default function SearchVolunteer() {
  const [isOpen, setIsOpen] = useState(false);
  const [isVisible, setIsVisible] = useState(false);
  const [state, setState] = useState({
    center: {
      lat: 33.450701,
      lng: 126.570667,
    },
  });
  const [shelters, setShelters] = useState([
    {
      center: {
        lat: 35.10134025316836,
        lng: 129.0167898528306,
      },
    },
    {
      center: {
        lat: 33.450701,
        lng: 126.570667,
      },
    },
  ]);
  const [clickPosition, setClickPosition] = useState({
    center: {
      lat: 35.10134025316836,
      lng: 129.0167898528306,
    },
  });
  const [shelterId, setShelterId] = useState();

  // const { data, isLoading } = useQuery({
  //   queryKey: ['getVolNoticePerMonth', state],
  //   queryFn: () => getVolNoticePerMonth(state.center.lat.toString(), state.center.lng.toString()),
  // });

  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', state],
    queryFn: () => getVolNoticeList(0, 0, 0, clickPosition.center.lat.toString(), clickPosition.center.lng.toString()),
  });

  // const { shelterdata } = useQuery({
  //   queryKey: ['getShelterDetail', shelterId],
  //   queryFn: () => getShelterDetail(shelterId),
  // });

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
  if (isLoading) return;
  console.log('data', data);

  const EventMarkerContainer = ({ position, content }) => {
    const [isVisible, setIsVisible] = useState(false);

    return (
      <MapMarker
        position={position} // 마커를 표시할 위치
        // @ts-ignore
        image={{
          src: 'https://i.ibb.co/XCjM1ss/003.png',
          size: {
            width: 34,
            height: 36,
          },
        }}
        onClick={() => {
          setIsVisible(true);
          setShelterId(data[content].shelterId);
        }}
      >
        {isVisible ? (
          <div style={{ minWidth: '150px' }}>
            <img
              alt="close"
              width="14"
              height="13"
              src="https://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif"
              style={{
                position: 'absolute',
                right: '5px',
                top: '5px',
                cursor: 'pointer',
              }}
              onClick={() => setIsVisible(false)}
            />

            <div>{data[content].name}</div>
            <div>{data[content].shelterId}</div>
            {/* 클릭하면 모달로 */}
          </div>
        ) : null}
      </MapMarker>
    );
  };

  return (
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
        strokeColor={'#ffd80b'}
        strokeOpacity={0.1}
        strokeStyle={'solid'}
        fillColor={'#ffd80b'}
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
      {shelters.map((position, index) => (
        <EventMarkerContainer
          key={`EventMarkerContainer-${position.center.lat}-${position.center.lng}`}
          position={position.center}
          content={index}
        />

        // <MapMarker
        //   key={index}
        //   position={position.center}
        //   onClick={() => {
        //     setIsOpen(true);
        //     setIsVisible(true);
        //     setClickPosition(position);
        //   }}
        //   image={{
        //     src: 'https://i.ibb.co/XCjM1ss/003.png',
        //     size: {
        //       width: 34,
        //       height: 36,
        //     },
        //   }}
        // >
        //   {isOpen && (
        //     <div style={{ minWidth: '150px' }}>
        //       <img
        //         alt="close"
        //         width="14"
        //         height="13"
        //         src="https://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif"
        //         style={{
        //           position: 'absolute',
        //           right: '5px',
        //           top: '5px',
        //           cursor: 'pointer',
        //         }}
        //         onClick={() => setIsOpen(false)}
        //       />
        //       <div style={{ padding: '5px', color: '#000' }}>Hello World!</div>
        //     </div>
        //   )}
        // </MapMarker>
      ))}
    </Map>
  );
}
