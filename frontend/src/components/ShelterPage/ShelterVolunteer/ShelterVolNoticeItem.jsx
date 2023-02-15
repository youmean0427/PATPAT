import VolunteerCard from 'components/Common/Card/ConsultingCard copy';
import React from 'react';
import Shelter from 'assets/images/shelter.png';
import styles from './ShelterVolNoticeItem.module.scss';
import { useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import { applyVolReservation } from 'apis/api/volunteer';
import { toast } from 'react-toastify';
import { data } from 'jquery';
export default function ShelterVolNoticeItem({ item }) {
  const { title, date, scheduleId, noticeId, guideLine, capacity, totalCapacity, startTime, endTime } = item;
  const [value, setValue] = useState(0);
  const { mutate } = useMutation(['applyVolReservation'], data => applyVolReservation(data), {
    onSuccess: ({ data }) => {
      if (data.code === '011') {
        toast(data.message, { type: 'error' });
      } else {
        toast('봉사 신청이 완료되었습니다.', { type: 'success' });
      }
    },
  });
  const handleClick = () => {
    const data = {
      scheduleId,
      capacity: value,
    };
    mutate(data);
  };
  return (
    <VolunteerCard>
      <div className={styles.container}>
        <div className={styles.user}>
          <img src={Shelter} alt="이미지" />
        </div>
        <div className={styles.desc}>
          <div className={styles.title}>{title}</div>
          <div className={styles.date}>{date}</div>
          <div className={styles.time}>
            {startTime} : {endTime}
          </div>
          <div className={styles.bottom}>
            <div>
              <label>
                인원{capacity}/{totalCapacity}
              </label>
              <div>
                <input type="text" onChange={e => setValue(e.target.value)} value={value} />
                <button onClick={handleClick}>신청</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </VolunteerCard>
  );
}
