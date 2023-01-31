import { useQuery } from '@tanstack/react-query';
import { getMyConsultations } from 'apis/api/consulting';
import React from 'react';
import styles from './ReservationList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';

export default function ConsultingList() {
  const userId = 1;

  const { data, isLoading } = useQuery({
    queryKey: ['myConsultingList'],
    queryFn: () => getMyConsultations(userId, 0),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      {data.map(item => (
        <ConsultingItem key={item.consultingId} item={item} />
      ))}
    </div>
  );
}
