import { useQuery } from '@tanstack/react-query';
import { getVolReservationOfUserDetail } from 'apis/api/volunteer';
import React, { useState } from 'react';
import styles from './VolunteerItem.module.scss';
import ConsultingCard from 'components/Common/Card/ConsultingCard';
import { formatDate } from 'utils/formatDate';
import UserBadge from 'components/Common/UserBadge/UserBadge';
import Shelter from 'assets/images/shelter.png';
import DetailModal from 'components/Common/DetailModal';

export default function VolunteerItem({ item }) {
  const { startTime, endTime, volunteerDate, scheduleId } = item;
  const date = [item.endTime[0], item.endTime[1], item.endTime[2]];
  const startHour = item.startTime[3] <= 9 ? '0' + item.startTime[3] : item.startTime[3];
  const startMinute = item.startTime[4] <= 9 ? '0' + item.startTime[4] : item.startTime[4];
  const endHour = item.endTime[3] <= 9 ? '0' + item.endTime[3] : item.endTime[3];
  const endMonute = item.endTime[4] <= 9 ? '0' + item.endTime[4] : item.endTime[4];
  const [modal, setModal] = useState(false);
  const { data, isLoading } = useQuery({
    queryKey: ['getMyConsultations', scheduleId],
    queryFn: () => {
      return getVolReservationOfUserDetail(scheduleId);
    },
  });

  const openModal = idx => {
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };
  if (isLoading) return;

  return (
    <ConsultingCard>
      <div className={styles.info} onClick={() => openModal()}>
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
              <span className={styles['desc-item-title']}>{volunteerDate}</span>
            </div>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>
                {startTime} ~ {endTime} 예정
              </span>
            </div>
          </div>
        </div>
      </div>
      <UserBadge state={item.reservationState} stateCode={item.reservationStateCode} items={null} />
      <DetailModal open={modal} close={closeModal} title={'봉사공고 상세'}>
        <div className={styles.desc}>
          <div className={styles.content}>
            <div className={styles['desc-item']}>
              <span className={styles.name}>{item.shelterName}</span>
            </div>
            <div className={styles['desc-item']}>
              <span className={styles.address}>{item.shelterAddress}</span>
            </div>
            <UserBadge state={item.reservationState} stateCode={item.reservationStateCode} items={null} />
          </div>
        </div>
        <hr className={styles.line} />
        <div>
          <div className={styles['res-date']}>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>{volunteerDate}</span>
            </div>
            <div className={styles['desc-item']}>
              <span className={styles['desc-item-title']}>
                {startTime} ~ {endTime} 예정
              </span>
            </div>
          </div>
          <div className={styles['guide-line']} dangerouslySetInnerHTML={{ __html: data.guideLine }}></div>
        </div>
      </DetailModal>
    </ConsultingCard>
  );
}
