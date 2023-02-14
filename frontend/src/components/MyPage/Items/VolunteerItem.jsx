import React from 'react';
import styles from './VolunteerItem.module.scss';
import ConsultingCard from 'components/Common/Card/ConsultingCard';
import { formatDate } from 'utils/formatDate';
import UserBadge from 'components/Common/UserBadge/UserBadge';
import Shelter from 'assets/images/shelter.png';
export default function VolunteerItem({ item }) {
  const date = [item.endTime[0], item.endTime[1], item.endTime[2]];
  const startHour = item.startTime[3] <= 9 ? '0' + item.startTime[3] : item.startTime[3];
  const startMinute = item.startTime[4] <= 9 ? '0' + item.startTime[4] : item.startTime[4];
  const endHour = item.endTime[3] <= 9 ? '0' + item.endTime[3] : item.endTime[3];
  const endMonute = item.endTime[4] <= 9 ? '0' + item.endTime[4] : item.endTime[4];

  return (
    <ConsultingCard>
      <div className={styles.info}>
        <div className={styles.user}>
          <img src={Shelter} alt="이미지" />
        </div>
        <div className={styles.desc}>
          <div className={styles['desc-item']}>
            <span>{item.shelterName}</span>
          </div>
          <div className={styles['desc-item']}>
            <span className={styles.address}>{item.shelterAddress}</span>
          </div>
          <div className={styles['res-date']}>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>{formatDate(date)}</span>
            </div>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>
                {startHour + ':' + startMinute + ' ~ ' + endHour + ':' + endMonute} 예정
              </span>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.state}></div>
      <UserBadge state={item.reservationState} stateCode={item.reservationStateCode} />
    </ConsultingCard>
  );
}
