import { useQuery } from '@tanstack/react-query';
import { getProtectDetail } from 'apis/api/protect';
import React from 'react';
import { useParams } from 'react-router';
import styles from './ProtectsDetail.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import ImageSelect from './ImageSelect';
import DetailContent from './DetailContent';
import ConsultingReservation from './ConsultingReservation';

export default function ProtectsDetail() {
  const protectId = useParams();
  const userInfo = JSON.parse(localStorage.getItem('user'));
  const isLogin = userInfo === null ? false : true;
  const { data, isLoading } = useQuery({
    queryKey: ['getProtectDetail', protectId.id],
    queryFn: () => getProtectDetail(protectId.id, isLogin),
  });

  if (isLoading) return;

  return (
    <ShelterContainer title="보호동물 정보">
      <main className={styles.main}>
        <div className={styles['main-images']}>
          <ImageSelect images={data.fileUrlList} />
        </div>
        <div className={styles['main-content']}>
          <DetailContent data={data} />
        </div>
      </main>
      <hr className={styles.line} />
      <div className={styles['info-contnet']} dangerouslySetInnerHTML={{ __html: data.infoContent }}></div>
      <ConsultingReservation data={data} />
    </ShelterContainer>
  );
}
