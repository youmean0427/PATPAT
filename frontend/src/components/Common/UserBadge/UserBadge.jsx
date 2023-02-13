import React from 'react';
import styles from './UserBadge.module.scss';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { setUser } from 'redux/user';
import { updateConsultant } from 'apis/api/consulting';

export default function ConsultingBadge({ state, stateCode, data }) {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const customStyle = () => {
    if (stateCode === 0) {
      return `${styles.badge} ${styles.zero}`;
    } else if (stateCode === 1) {
      return `${styles.badge} ${styles.one}`;
    } else if (stateCode === 2) {
      return `${styles.badge} ${styles.two}`;
    } else if (stateCode === 3) {
      return `${styles.badge} ${styles.three}`;
    } else if (stateCode === 4) {
      return `${styles.badge} ${styles.four}`;
    } else if (stateCode === 5) {
      return `${styles.badge} ${styles.five}`;
    } else {
      return `${styles.badge} ${styles.six}`;
    }
  };

  const handleConsulting = () => {
    dispatch(setUser({ resIsShelter: false, resShelterId: data.shelterId, resUserName: data.shelterName }));
    const newData = {
      address: data.address,
      consultingDate: data.consultingDate,
      consultingId: data.consultingId,
      registDate: data.registDate,
      shelterDogId: data.shelterDogId,
      shelterDogName: data.shelterDogName,
      shelterId: data.shelterId,
      shelterName: data.shelterName,
      state: data.state,
      stateCode: 4,
      time: data.time,
      timeCode: data.timeCode,
      userExp: data.userExp,
      userId: data.userId,
      userName: data.userName,
      userProfileUrl: data.userProfileUrl,
    };
    updateConsultant(data.consultingId, newData);
    navigate('/consulting/meeting');
  };

  return (
    <div className={customStyle()} onClick={stateCode === 3 ? handleConsulting : null}>
      {state === '방생성' ? '방참가' : state}
    </div>
  );
}
