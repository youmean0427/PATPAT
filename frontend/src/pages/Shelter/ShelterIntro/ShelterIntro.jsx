import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { useParams } from 'react-router-dom';
import './ShelterIntro.scss';
import styles from './ShelterIntro.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import ImageGallery from 'react-image-gallery';

export default function ShelterIntro() {
  const { shelterId } = useParams();

  const parsingImageList = list => {
    return list.map(item => {
      return { original: item.filePath, thumbnail: item.filePath };
    });
  };
  const { data, isLoading } = useQuery(['getShelterDetailInfo', shelterId], () => getShelterDetail(shelterId), {
    staleTime: 1000 * 60 * 5,
  });
  if (isLoading) return;
  return (
    <ShelterContainer title="보호소 정보">
      <main className={styles.main}>
        <ImageGallery originalWidth="600px" items={parsingImageList(data.imageList)} />
        <div className={styles['info-box']}>
          <div className={styles['info-item']}>
            <div className={styles['info-label']}>담당자 :</div>
            <div className={styles['info-value']}>문석환</div>
          </div>
          <div className={styles['info-item']}>
            <div className={styles['info-label']}>전화번호 : </div>
            <div className={styles['info-value']}>010-9281-4031</div>
          </div>
          <div className={styles['info-item']}>
            <div className={styles['info-label']}>주소 : </div>
            <div className={styles['info-value']}>{data.address}</div>
          </div>
        </div>
      </main>
    </ShelterContainer>
  );
}
