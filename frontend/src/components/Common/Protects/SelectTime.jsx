import { useQuery } from '@tanstack/react-query';
import { getShelterConsultationsTime } from 'apis/api/consulting';
import moment from 'moment';
import React from 'react';
import styles from './SelectTime.module.scss';

export default function SelectTime({ shelterId, value }) {
  const { data, isLoading } = useQuery({
    queryKey: ['getShelterConsultationsTime', moment(value).format('YYYY-MM-DD')],
    queryFn: () => getShelterConsultationsTime(shelterId, moment(value).format('YYYY-MM-DD')),
  });

  if (isLoading) return;
  console.log(data);
  return (
    <div className={styles['consulting-time']}>
      <div className={styles.morning}>
        <p>오전</p>
        <div className={styles.times}>
          <div className={data[0].active ? styles.activate : styles.deactivate}>10:00</div>
          <div className={data[1].active ? styles.activate : styles.deactivate}>11:00</div>
        </div>
      </div>
      <div className={styles.afternoon}>
        <p>오후</p>
        <div className={styles.times}>
          <div className={data[2].active ? styles.activate : styles.deactivate}>14:00</div>
          <div className={data[3].active ? styles.activate : styles.deactivate}>15:00</div>
          <div className={data[4].active ? styles.activate : styles.deactivate}>16:00</div>
          <div className={data[5].active ? styles.activate : styles.deactivate}>17:00</div>
        </div>
      </div>
    </div>
  );
}
