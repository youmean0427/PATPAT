import { useQuery } from '@tanstack/react-query';

import { getVolNoticePerMonth } from 'apis/api/volunteer';

import styles from './SearchVolunteerMark.module.scss';
import React, { useState } from 'react';
import { MapMarker } from 'react-kakao-maps-sdk';
import SearchVolunteerList from './SearchVolunteerList';

export default function SearchVolunteerMark({ latitude, longitude, shelterId, name }) {
  const [isVisible, setIsVisible] = useState(false);
  const position = { lat: latitude, lng: longitude };

  // Today Date
  let now = new Date();
  let todayYear = now.getFullYear();
  let todayMonth = now.getMonth() + 1;
  console.log(todayYear, todayMonth);

  if (todayMonth < 10) {
    todayMonth = `0${todayMonth}`;
  }

  // useQuery
  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', shelterId],
    queryFn: () => getVolNoticePerMonth(shelterId, todayYear.toString(), todayMonth),
  });

  if (isLoading) return;
  console.log('mark', data);
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
      }}
    >
      {isVisible ? (
        <div className={styles.infoContainer}>
          <img
            alt="close"
            src="https://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif"
            style={{
              position: 'absolute',

              right: '5px',
              top: '5px',
              cursor: 'pointer',
            }}
            onClick={() => setIsVisible(false)}
          />
          {/*여기 봉사공고 리스트  */}

          <SearchVolunteerList items={data} />
        </div>
      ) : null}
    </MapMarker>
  );
}
