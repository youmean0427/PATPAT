import { createConsultant } from 'apis/api/consulting';
import React, { useState } from 'react';
import styles from './ConsultingReservation.module.scss';
import DetailModal from 'components/Common/DetailModal';
import Calendar from 'react-calendar';
import './Calendar.css';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import moment from 'moment';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import { AiOutlineClockCircle } from 'react-icons/ai';
import SelectTime from './SelectTime';

export default function ConsultingReservation({ data }) {
  const [modal, setModal] = useState(false);
  const [value, setValue] = useState(new Date());
  const [dateClick, setDateClick] = useState(false);
  const [timeClick, setTimeClick] = useState(false);
  const [selectTime, setSelectTime] = useState();
  const [click, setClick] = useState([false, false, false, false, false, false]);

  const userId = JSON.parse(localStorage.getItem('user')).userId;

  const openModal = idx => {
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };
  const save = () => {
    alert('신청되었습니다.');
    const sendData = {
      consultingDate: moment(value).format('YYYY-MM-DD'),
      shelterId: 316,
      timeCode: selectTime,
      shelterDogId: data.protectId,
      userid: userId,
    };
    createConsultant(sendData);
    closeModal();
  };

  const saveFunction = selTime => {
    setSelectTime(selTime);
  };

  return (
    <>
      <div className={styles.button}>
        <button className={styles.active} onClick={() => openModal()}>
          상담 신청하기
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
            {dateClick ? (
              <KeyboardArrowUpIcon
                onClick={() => setDateClick(cur => !cur)}
                className={`${styles.icon} ${styles.up}`}
                sx={{ fontSize: '20px' }}
              />
            ) : (
              <KeyboardArrowDownIcon
                onClick={() => setDateClick(cur => !cur)}
                className={`${styles.icon} ${styles.down}`}
                sx={{ fontSize: '20px' }}
              />
            )}
          </div>
          <hr className={styles['modal-line']} />
          <div className={dateClick ? styles.calendar : styles['no-calendar']}>
            <Calendar
              onChange={e => {
                setValue(e);
                setClick([false, false, false, false, false, false]);
              }}
              value={value}
              calendarType="US"
            />
          </div>

          <div className={styles.blank}></div>
          <span>
            <AiOutlineClockCircle className={styles.icon} sx={{ fontSize: '20px', color: '#694e4e' }} />
          </span>
          <span>시간 선택</span>
          {timeClick ? (
            <KeyboardArrowUpIcon
              onClick={() => setTimeClick(cur => !cur)}
              className={`${styles.icon} ${styles.up}`}
              sx={{ fontSize: '20px' }}
            />
          ) : (
            <KeyboardArrowDownIcon
              onClick={() => setTimeClick(cur => !cur)}
              className={`${styles.icon} ${styles.down}`}
              sx={{ fontSize: '20px' }}
            />
          )}
          <hr className={styles['modal-line']} />
          <div className={timeClick ? styles.selTime : styles['no-selTime']}>
            <SelectTime
              shelterId={data.shelterId}
              value={value}
              saveFunction={saveFunction}
              click={click}
              setClick={setClick}
            />
          </div>
        </div>
        <footer className={styles.footer}>
          <button className={styles.save} onClick={save}>
            신청
          </button>
          <button className={styles.close} onClick={closeModal}>
            취소
          </button>
        </footer>
      </DetailModal>
    </>
  );
}
