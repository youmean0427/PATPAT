import { useQuery } from '@tanstack/react-query';
import { getVolReservationOfUser } from 'apis/api/volunteer';
import React from 'react';
import styles from './ReservationList.module.scss';
import VolunteerItem from '../Items/VolunteerItem';

export default function VolunteerList() {
  const userId = 1;

  const { data, isLoading } = useQuery({
    queryKey: ['myVolunteerList'],
    queryFn: () => getVolReservationOfUser(userId),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      {data.map(item => (
        <VolunteerItem key={item.reservationId} item={item} />
      ))}
    </div>
  );
}
