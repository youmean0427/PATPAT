import React from 'react';
import styles from './ReservationItem.module.scss';
import ShelterImg from 'assets/images/shelter.png';

export default function VolunteerItem({ item }) {
  const { reservationId, reservationState, reservationStateCode, scheduleId, shelterAddress, shelterName, startTime } =
    item;

  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
        <img src={ShelterImg} alt="" />
      </div>
      <div className={styles.contents}>
        <p className={styles['shelter-name']}>{shelterName}</p>
        <p className={styles.address}>{shelterAddress}</p>
        <p className={styles.date}>
          {startTime[0]}.{startTime[1]}.{startTime[2]}
        </p>
        <p className={styles.time}>
          {startTime[3]}:{startTime[4] <= 9 ? '0' + startTime[4] : startTime[4]}
        </p>
      </div>
      <div className={styles.buttons}>
        {reservationStateCode === 0 ? (
          <button
            className={styles.cancel}
            onClick={() => {
              alert('취소되었습니다.');
            }}
          >
            예약 취소
          </button>
        ) : (
          <button className={styles.cancel} style={{ visibility: 'hidden' }}></button>
        )}
        <button
          className={
            reservationStateCode === 0
              ? styles.state0
              : reservationStateCode === 1
              ? styles.state1
              : reservationStateCode === 2
              ? styles.state2
              : reservationStateCode === 3
              ? styles.state3
              : reservationStateCode === 4
              ? styles.state4
              : null
          }
        >
          {reservationState}
        </button>
      </div>
    </div>
  );
}
