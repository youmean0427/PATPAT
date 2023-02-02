import { useQuery } from '@tanstack/react-query';
import { getCountShelterByBreed } from 'apis/api/shelter';
import MbtiContainer from 'components/Common/MbtiContainer';
import SouthKorea from 'components/Common/SouthKorea';
import React from 'react';
import { useLocation } from 'react-router';
import styles from './MoreInfo.module.scss';
import { Tooltip } from 'react-tooltip';

export default function MoreInfo() {
  const {
    state: { breedId, breedName, breedImg },
  } = useLocation();
  const { data, isLoading } = useQuery(['searchBreedCountPerRegion'], () => getCountShelterByBreed(breedId));
  if (isLoading) return;

  return (
    <MbtiContainer>
      <div className={styles.card}>
        <div className={styles['img-box']}>
          <img src={breedImg} alt="견종 이미지" />
        </div>
        <div className={styles['desc-box']}>
          <div className={styles['desc-breed']}>{breedName}</div>
          <div className={styles['desc-count']}>
            PATPAT에 등록된 보호소 중 해당 견종을 보유한 곳은 총 <span>{data.totalCnt}개</span>입니다
          </div>
        </div>
      </div>
      <SouthKorea list={data.list} />
      <Tooltip place="bottom" effect="solid" type="warning" />
    </MbtiContainer>
  );
}
