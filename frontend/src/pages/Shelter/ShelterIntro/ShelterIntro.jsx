import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { useParams } from 'react-router-dom';
import './ShelterIntro.scss';
import styles from './ShelterIntro.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import useModal from 'hooks/useModal';
import EditInfoModal from 'components/Common/Modal/shelters/EditInfoModal';
import { checkMyShelter } from 'utils/checkMyShelter';
import OpenModalBtn from 'components/Common/Button/OpenModalBtn';
import ImageSlide from 'components/ShelterPage/ImageSlide';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { Map, MapMarker } from 'react-kakao-maps-sdk';

export default function ShelterIntro() {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  const { shelterId } = useParams();
  const myShelterId = useRecoilValue(myShelterIdState);

  const { data, isLoading } = useQuery(['getShelterDetailInfo', shelterId], () => getShelterDetail(shelterId), {
    staleTime: 1000 * 60 * 5,
  });
  if (isLoading) return;
  return (
    <ShelterContainer title="보호소 정보">
      <main className={styles.main}>
        <ImageSlide imageList={data.imageList} />
        <div className={styles['info-box']}>
          <div className={styles['title-box']}>
            <span>{data.name}</span>
            {checkMyShelter(shelterId, myShelterId) && <OpenModalBtn handleClickModalOpen={handleClickModalOpen} />}
          </div>
          <div className={styles.info}>
            <div className={styles['info-item']}>
              <div className={styles['info-value']}>{data.infoContent}</div>
            </div>
            <div className={styles['info-item']}>
              <div className={styles['info-label']}>담당자</div>
              <div className={styles['info-value']}>{data.ownerName}</div>
            </div>
            <div className={styles['info-item']}>
              <div className={styles['info-label']}>전화번호 : </div>
              <div className={styles['info-value']}>{data.phoneNumber}</div>
            </div>
          </div>
        </div>
      </main>
      {checkMyShelter(shelterId, myShelterId) && (
        <div className={styles.map}>
          <Map // 지도를 표시할 Container
            center={
              // 지도의 중심좌표
              { lat: data.latitude, lng: data.longitude }
            }
            style={{
              width: '100%',
              height: '100%',
            }}
            level={4} // 지도의 확대 레벨
          >
            <MapMarker
              position={{ lat: data.latitude, lng: data.longitude }}
              image={{
                src: 'https://i.ibb.co/z42FXX4/002-2.png', // 마커이미지의 주소입니다
                size: {
                  width: 64,
                  height: 64,
                }, // 마커이미지의 크기입니다
                options: {
                  offset: {
                    x: 27,
                    y: 69,
                  }, // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                },
              }}
            />
          </Map>
        </div>
      )}

      {isOpen && (
        <EditInfoModal
          data={data}
          isOpen={isOpen}
          handleClickModalClose={handleClickModalClose}
          shelterId={shelterId}
        />
      )}
    </ShelterContainer>
  );
}
