import React from 'react';
import styles from './VolunteerItem.module.scss';
import ConsultingCard from 'components/Common/Card/ConsultingCard';

export default function SearchShelterItem({ item }) {
  return (
    <ConsultingCard>
      <div className={styles.info}>
        <div className={styles.user}>
          <img src="" alt="이미지" />
        </div>
        <div className={styles.desc}>
          {/* <div className={styles['desc-item']}>
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
          </div> */}
        </div>
      </div>
    </ConsultingCard>
  );
}
