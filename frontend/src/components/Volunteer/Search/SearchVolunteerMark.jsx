import { useQuery } from '@tanstack/react-query';

import { getVolNoticePerMonth } from 'apis/api/volunteer';

import styles from './SearchVolunteerMark.module.scss';
import React, { useState } from 'react';
import { MapMarker } from 'react-kakao-maps-sdk';
import SearchVolunteerList from './SearchVolunteerList';

export default function SearchVolunteerMark({
  latitude,
  longitude,
  shelterId,
  markToPage,
  modalIndex,
  onClick,
  isClicked,
  onClose,
}) {
  const position = { lat: latitude, lng: longitude };

  const listToMark = x => {
    markToPage(x);
    // 'Mark', x);
  };

  // Today Date
  let now = new Date();
  let todayYear = now.getFullYear();
  let todayMonth = now.getMonth() + 1;

  if (todayMonth < 10) {
    todayMonth = `0${todayMonth}`;
  }

  // useQuery
  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', shelterId],
    queryFn: () => getVolNoticePerMonth(shelterId, todayYear.toString(), todayMonth),
  });

  if (isLoading) return;

  return (
    <div>
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
        onClick={onClick}
      >
        {isClicked ? (
          <div className={styles.container}>
            {/* <CustomOverlayMap // 커스텀 오버레이를 표시할 Container
              position={{ lat: position.lat, lng: position.lng }} // 커스텀 오버레이가 표시될 위치
              yAnchor={1.125} // 마커와의 간격을 조정할 수 있다
            > */}
            <div className={styles.infoContainer}>
              <div className={styles.closeButton}>
                <div>
                  <button onClick={onClose}>
                    {/* <CgCloseO size="24" color="gray" />
                     */}
                    &times;
                  </button>
                </div>
              </div>
              {/*여기 봉사공고 리스트  */}
              <SearchVolunteerList items={data} listToMark={listToMark} />
            </div>
            {/* </CustomOverlayMap> */}
          </div>
        ) : null}
      </MapMarker>
    </div>
  );
}
