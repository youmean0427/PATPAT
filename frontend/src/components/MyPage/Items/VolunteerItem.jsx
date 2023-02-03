import React from 'react';
import styles from './ReservationItem.module.scss';
import ShelterImg from 'assets/images/shelter.png';

export default function VolunteerItem({ item }) {
  const { shelterName, address, startTime, endTime, state } = item;
  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
        <img src={ShelterImg} alt="" />
      </div>
      <div className={styles.contents}>
        <p className={styles['user-name']}>{shelterName}</p>
        <p>{address}</p>
        <p>
          {startTime[0]}.{startTime[1]}.{startTime[2]} {startTime[3]}:
          {startTime[4] <= 9 ? '0' + startTime[4] : startTime[4]} ~ {endTime[3]}:
          {endTime[4] <= 9 ? '0' + endTime[4] : endTime[4]}
        </p>
      </div>
      <div className={styles.buttons}>
        <button className={styles.cancel}>예약 취소</button>
        <button className={state === 0 ? styles.state0 : state === 1 ? styles.state1 : styles.state2}>
          {state === 0 ? '대기' : state === 1 ? '승인' : '거절'}
        </button>
      </div>
    </div>
  );
}
