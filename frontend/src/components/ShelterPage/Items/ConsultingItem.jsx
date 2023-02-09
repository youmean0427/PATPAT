import { updateConsultant } from 'apis/api/consulting';
import React, { useState } from 'react';
import styles from './ConsultingItem.module.scss';
import ConsultingImg from 'assets/images/consulting.png';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { setConsulting } from 'redux/consulting';
import { useMutation } from '@tanstack/react-query';

export default function ConsultingItem({ item }) {
  const { consultingId, stateCode, registDate, userId, userName, timeCode } = item.item;
  const shelterId = item.shelterId;
  const shelterName = item.shelterName;
  const [click, setClick] = useState(false);
  const [btnState, setBtnState] = useState(stateCode);
  const { mutate } = useMutation((consultingId, data) => updateConsultant(consultingId, data), {
    onSuccess: data => {
      dispatch(setConsulting({ resShelterId: shelterId, resUserName: shelterName }));
      navigate('/consulting/meeting');
    },
  });
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const startConsulting = () => {
    setBtnState(8);
    mutate(consultingId, { stateCode: 8 });
  };

  return (
    <div className={styles.items}>
      <div className={styles['consulting-img']}>
        <img src={ConsultingImg} alt="" />
      </div>
      <div className={styles.contents}>
        <p className={styles['user-name']}>{userName}</p>
        <p>
          {registDate[0]}.{registDate[1] <= 9 ? '0' + registDate[1] : registDate[1]}.
          {registDate[2] <= 9 ? '0' + registDate[2] : registDate[2]}
        </p>
        <p>
          {timeCode === 0
            ? '10:00 ~ 11:00'
            : timeCode === 1
            ? '14:00 ~ 15:00'
            : timeCode === 2
            ? '15:00 ~ 16:00'
            : timeCode === 3
            ? '16:00 ~ 17:00'
            : '18:00 ~ 19:00'}
        </p>
      </div>
      <div className={styles.buttons}>
        {stateCode === 5 ? (
          <button onClick={startConsulting} className={styles.state5}>
            방생성
          </button>
        ) : (
          <button
            onClick={() => setClick(cur => !cur)}
            className={
              btnState === 0
                ? styles.state0
                : btnState === 1
                ? styles.state1
                : btnState === 2
                ? styles.state2
                : btnState === 3
                ? styles.state3
                : btnState === 4
                ? styles.state4
                : null
            }
          >
            {btnState === 0
              ? '대기'
              : btnState === 1
              ? '승인'
              : btnState === 2
              ? '거부'
              : btnState === 3
              ? '미완료'
              : btnState === 4
              ? '완료'
              : null}
          </button>
        )}
      </div>
      <div className={click ? styles['state-buttons'] : styles['hide-buttons']}>
        <button
          onClick={() => {
            setBtnState(0);
            setClick(false);
            const data = JSON.stringify({ shelterId, userName, btnState, timeCode, registDate });
            updateConsultant(consultingId, data);
          }}
          className={styles.state0}
        >
          대기
        </button>
        <button
          onClick={() => {
            setBtnState(1);
            setClick(false);
            const data = JSON.stringify({
              consultingId,
              shelterId,
              shelterName,
              btnState,
              registDate,
              userId,
              userName,
              timeCode,
            });
            updateConsultant(consultingId, data);
          }}
          className={styles.state1}
        >
          승인
        </button>
        <button
          onClick={() => {
            setBtnState(2);
            setClick(false);
            const data = JSON.stringify({
              consultingId,
              shelterId,
              shelterName,
              btnState,
              registDate,
              userId,
              userName,
              timeCode,
            });
            updateConsultant(consultingId, data);
          }}
          className={styles.state2}
        >
          거부
        </button>
        <button
          onClick={() => {
            setBtnState(3);
            setClick(false);
            const data = JSON.stringify({
              consultingId,
              shelterId,
              shelterName,
              btnState,
              registDate,
              userId,
              userName,
              timeCode,
            });
            updateConsultant(consultingId, data);
          }}
          className={styles.state3}
        >
          미완료
        </button>
        <button
          onClick={() => {
            setBtnState(4);
            setClick(false);
            const data = JSON.stringify({
              consultingId,
              shelterId,
              shelterName,
              btnState,
              registDate,
              userId,
              userName,
              timeCode,
            });
            updateConsultant(consultingId, data);
          }}
          className={styles.state4}
        >
          완료
        </button>
        {stateCode === 5 ? (
          <button className={styles.state5}>방생성</button>
        ) : (
          <button disabled={true} className={styles.disabled}>
            방생성
          </button>
        )}
      </div>
    </div>
  );
}
