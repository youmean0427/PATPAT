import React from 'react';
import styles from './ReservationItem.module.scss';
import ConsultingImg from 'assets/images/shelter.png';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { setConsulting } from 'redux/consulting';

export default function Consulting({ item }) {
  const { consultingId, shelterId, shelterName, address, timeCode, consultingDate, stateCode, state } = item;

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const startConsulting = () => {
    dispatch(setConsulting({ resShelterId: shelterId, resUserName: shelterName }));
    navigate('/consulting/meeting');
  };

  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
        <img src={ConsultingImg} alt="" />
      </div>
      <div className={styles.contents}>
        <p className={styles['shelter-name']}>{shelterName}</p>
        <p className={styles.address}>{address}</p>
        <p className={styles.date}>
          {consultingDate[0]}.{consultingDate[1] <= 9 ? '0' + consultingDate[1] : consultingDate[1]}.
          {consultingDate[2] <= 9 ? '0' + consultingDate[2] : consultingDate[2]}
        </p>
        <p className={styles.time}>
          {timeCode === 0
            ? '10 : 00 ~ 11 : 00'
            : timeCode === 1
            ? '14 : 00 ~ 15 : 00'
            : timeCode === 2
            ? '15 : 00 ~ 16 : 00'
            : timeCode === 3
            ? '16 : 00 ~ 17 : 00'
            : '17 : 00 ~ 18 : 00'}
        </p>
      </div>
      <div className={styles.buttons}>
        {stateCode === 0 ? (
          <button className={styles.cancel}>예약 취소</button>
        ) : (
          <button className={styles.cancel} style={{ visibility: 'hidden' }}></button>
        )}
        {stateCode === 5 ? (
          <button disabled={true} onClick={startConsulting} className={styles.disabled}>
            방참가
          </button>
        ) : stateCode === 8 ? (
          <button onClick={startConsulting} className={styles.state8}>
            방생성
          </button>
        ) : (
          <button
            className={
              stateCode === 0
                ? styles.state0
                : stateCode === 1
                ? styles.state1
                : stateCode === 2
                ? styles.state2
                : stateCode === 3
                ? styles.state3
                : stateCode === 4
                ? styles.state4
                : null
            }
          >
            {stateCode === 0
              ? '대기'
              : stateCode === 1
              ? '승인'
              : stateCode === 2
              ? '거부'
              : stateCode === 3
              ? '미완료'
              : stateCode === 4
              ? '완료'
              : null}
          </button>
        )}
      </div>
    </div>
  );
}
