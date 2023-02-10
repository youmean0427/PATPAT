import React from 'react';
import styles from './ReservationItem.module.scss';
import ShelterImg from 'assets/images/shelter.png';

export default function VolunteerItem({ item }) {
  console.log(item);
  const { shelterName, address, startTime, endTime, state } = item;
  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
        <img src={ShelterImg} alt="" />
      </div>
      <div className={styles.contents}>
        <p className={styles['shelter-name']}>{shelterName}</p>
        <p>{address}</p>
        <p>
          {startTime[0]}.{startTime[1]}.{startTime[2]} {startTime[3]}:
          {startTime[4] <= 9 ? '0' + startTime[4] : startTime[4]} ~ {endTime[3]}:
          {endTime[4] <= 9 ? '0' + endTime[4] : endTime[4]}
        </p>
      </div>
      <div className={styles.buttons}>
        {state === 0 || state === 1 ? (
          <button className={styles.cancel}>예약 취소</button>
        ) : (
          <button className={styles.cancel} style={{ visibility: 'hidden' }}></button>
        )}
        <button
          className={
            state === 0
              ? styles.state0
              : state === 1
              ? styles.state1
              : state === 2
              ? styles.state2
              : state === 3
              ? styles.state3
              : state === 4
              ? styles.state4
              : null
          }
        >
          {state === 0
            ? '대기'
            : state === 1
            ? '승인'
            : state === 2
            ? '거부'
            : state === 3
            ? '미완료'
            : state === 4
            ? '완료'
            : null}
        </button>
      </div>
    </div>
  );
}
