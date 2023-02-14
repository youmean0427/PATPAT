import React from 'react';
import styles from './ConsultingItem.module.scss';
import ConsultingCard from 'components/Common/Card/ConsultingCard';
import { formatDate, formatTime } from 'utils/formatDate';
import UserBadge from 'components/Common/UserBadge/UserBadge';
import Shelter from 'assets/images/shelter.png';
export default function ConsultingItem({ item }) {
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
          <div className={styles['res-date']}>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>{formatDate(item.consultingDate)}</span>
            </div>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>{formatTime(item.timeCode)} 예정</span>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.state}></div>
      <UserBadge
        state={item.state}
        stateCode={item.stateCode}
        shelterId={item.shelterId}
        shelterName={item.shelterName}
      />
    </ConsultingCard>
  );
}
