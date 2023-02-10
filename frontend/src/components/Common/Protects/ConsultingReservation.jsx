import { useQuery } from '@tanstack/react-query';
import { getShelterConsultationsTime } from 'apis/api/consulting';
import React, { useState, useEffect } from 'react';
import styles from './ConsultingReservation.module.scss';
import DetailModal from 'components/Common/DetailModal';
import Calendar from 'react-calendar';
import './Calendar.css';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import moment from 'moment';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import SelectTime from './SelectTime';

export default function ConsultingReservation({ data }) {
  const [modal, setModal] = useState(false);
  const [value, setValue] = useState(new Date());
  const [time, setTime] = useState();
  const [dateClick, setDateClick] = useState(false);

  const openModal = idx => {
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };

  return (
    <>
      <div className={styles.button}>
        <button className={styles.active} onClick={() => openModal()}>
          내 가족 찾기
        </button>
      </div>
      <DetailModal open={modal} close={closeModal} title="상담 신청">
        <div className={styles['select-date']}>
          <div className={styles['select-label']}>
            <span>
              <CalendarMonthIcon className={styles.icon} sx={{ fontSize: '20px', color: '#694e4e' }} />
            </span>
            <span>날짜 선택</span>
            <span className={styles.date}>{moment(value).format('YYYY-MM-DD')}</span>
            <KeyboardArrowUpIcon className={`${styles.icon} ${styles.up}`} sx={{ fontSize: '20px' }} />
            <KeyboardArrowDownIcon className={`${styles.icon} ${styles.down}`} sx={{ fontSize: '20px' }} />
          </div>
          <hr className={styles['modal-line']} />
          <Calendar
            className={styles.calendar}
            onChange={e => {
              setValue(e);
            }}
            value={value}
            calendarType="US"
          />
          <hr className={styles['modal-line']} />
          <div className={styles.blank}></div>
          <span>
            <AccessTimeIcon className={styles.icon} sx={{ fontSize: '20px', color: '#694e4e' }} />
          </span>
          <span>시간 선택</span>
          <KeyboardArrowUpIcon className={`${styles.icon} ${styles.up}`} sx={{ fontSize: '20px' }} />
          <KeyboardArrowDownIcon className={`${styles.icon} ${styles.down}`} sx={{ fontSize: '20px' }} />
          <SelectTime shelterId={data.shelterId} value={value} />
        </div>
      </DetailModal>
    </>
  );
}
