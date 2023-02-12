import { useQuery } from '@tanstack/react-query';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import React, { useState, useEffect } from 'react';
import { Map, MapMarker, Circle } from 'react-kakao-maps-sdk';
import styles from './SearchVolunteer.module.scss';
export default function SearchVolunteer() {
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
  ]);

  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', state],
    queryFn: () => getVolNoticePerMonth(state.center.lat.toString(), state.center.lng.toString()),
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

  if (isLoading) return;
  console.log(data);
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
        <MapMarker
          key={index}
          position={position.center}
          image={{
            src: 'https://i.ibb.co/XCjM1ss/003.png',
            size: {
              width: 34,
              height: 36,
            },
          }}
        />
      ))}
    </Map>
  );
}
