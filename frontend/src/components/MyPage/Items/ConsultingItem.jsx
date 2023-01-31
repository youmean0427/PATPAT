import React from 'react';
import styles from './ReservationItem.module.scss';
import ReservationCard from 'components/Common/ReservationCard';
import ConsultingImg from 'assets/images/consulting.png';

export default function Consulting({ item }) {
  const { shelterName, address, date, startTime, endTime, state } = item;

  return (
    <ReservationCard>
      <div className={styles.card}>
        <div className={styles['shelter-img']}>
          <img src={ConsultingImg} alt="" />
        </div>
        <div className={styles.contents}>
          <p className={styles['shelter-name']}>{shelterName}</p>
          <p>{address}</p>
          <p>
            {date[0]}.{date[1]}.{date[2]} {startTime[0]}:{startTime[1] <= 9 ? '0' + startTime[1] : startTime[1]} ~{' '}
            {endTime[0]}:{endTime[1] <= 9 ? '0' + endTime[1] : endTime[1]}
          </p>
        </div>
        <div className={styles.buttons}>
          <button className={styles.cancel}>예약 취소</button>
          <button className={state === 0 ? styles.state0 : state === 1 ? styles.state1 : styles.state2}>
            {state === 0 ? '대기' : state === 1 ? '승인' : '거절'}
          </button>
        </div>
      </div>
    </ReservationCard>
  );
}
