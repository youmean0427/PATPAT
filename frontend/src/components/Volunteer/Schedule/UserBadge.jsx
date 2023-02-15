import React from 'react';
import styles from './UserBadge.module.scss';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { setUser } from 'redux/user';
import { updateConsultant } from 'apis/api/consulting';

export default function ConsultingBadge({ state, stateCode, items }) {
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
    dispatch(setUser({ resIsShelter: false, resShelterId: items.shelterId, resUserName: items.shelterName }));
    const newitems = {
      address: items.address,
      consultingDate: items.consultingDate,
      consultingId: items.consultingId,
      registDate: items.registDate,
      shelterDogId: items.shelterDogId,
      shelterDogName: items.shelterDogName,
      shelterId: items.shelterId,
      shelterName: items.shelterName,
      state: items.state,
      stateCode: 4,
      time: items.time,
      timeCode: items.timeCode,
      userExp: items.userExp,
      userId: items.userId,
      userName: items.userName,
      userProfileUrl: items.userProfileUrl,
    };
    updateConsultant(items.consultingId, newitems);
    navigate('/consulting/meeting');
  };

  return (
    <div className={customStyle()} onClick={stateCode === 3 ? handleConsulting : null}>
      {state === '방생성' ? '방참가' : state}
    </div>
  );
}
