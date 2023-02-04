import React from 'react';
import styles from './ReservationItem.module.scss';
import ConsultingImg from 'assets/images/consulting.png';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { setConsulting } from 'redux/consulting';

export default function Consulting({ item }) {
  const { consultingId, shelterId, shelterName, userName, address, date, startTime, endTime, state } = item;

  const navigate = useNavigate();
  const dispatch = useDispatch();
  console.log(shelterId);
  const startConsulting = () => {
    dispatch(setConsulting({ resShelterId: shelterId, resUserName: userName }));
    navigate('/consulting/meeting');
  };

  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
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
        {state === 0 || state === 1 ? (
          <button className={styles.cancel}>예약 취소</button>
        ) : (
          <button className={styles.cancel} style={{ visibility: 'hidden' }}></button>
        )}
        {state === 5 ? (
          <button onClick={startConsulting} className={styles.state5}>
            방참가
          </button>
        ) : (
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
        )}
      </div>
    </div>
  );
}
