import { useQuery } from '@tanstack/react-query';
import { getMyConsultations } from 'apis/api/consulting';
import React from 'react';
import styles from './ReservationList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';

export default function ConsultingList({ userId }) {
  const { data, isLoading } = useQuery({
    queryKey: ['myConsultingList'],
    queryFn: () => getMyConsultations(20, 0, userId),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      {data.length === 0 ? (
        <div className={styles['no-data']}>신청한 상담 정보가 없습니다.</div>
      ) : (
        data.map(item => <ConsultingItem key={item.consultingId} item={item} />)
      )}
    </div>
  );
}
