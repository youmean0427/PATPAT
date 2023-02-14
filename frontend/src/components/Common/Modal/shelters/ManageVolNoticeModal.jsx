import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { getVolNoticeInfoPerDay, updateVolNoticeSchedule } from 'apis/api/volunteer';
import React, { useEffect } from 'react';
import { useState } from 'react';
import ModalFrame from '../ModalFrame';
import { DesktopTimePicker } from '@mui/x-date-pickers/DesktopTimePicker';
import styles from './ManageVolNoticeModal.module.scss';
import { TextField } from '@mui/material';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';
import { toast } from 'react-toastify';
dayjs.extend(utc);
dayjs.extend(timezone);

export default function ManageVolNoticeModal({ noticeId, isOpen, handleClickModalClose }) {
  const [sIdx, setSIdx] = useState(0);
  const { data, isLoading } = useQuery(['detailNotice', noticeId], () => getVolNoticeInfoPerDay(noticeId, 4, 0));
  if (isLoading) return;
  const { noticeId: id, title, schedules, volunteerDate } = data;
  console.log(schedules);
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={600} height={600}>
      <div className={styles.container}>
        <div className={styles.title}>공고 관리</div>
        <div className={styles.notice}>
          <span>공고 제목</span>
          <span>{title}</span>
          <span>날짜</span>
          <span>{volunteerDate}</span>
        </div>
        <div className={styles['btn-wrap']}>
          {schedules?.map((item, index) => {
            console.log(item);
            return (
              <button className={styles.btn} onClick={() => setSIdx(index)} key={index}>
                {`${item.startTime[3] + 9 >= 24 ? item.startTime[3] + 9 - 24 : item.startTime[3] + 9}시 ~ ${
                  item.endTime[3] + 9 >= 24 ? item.endTime[3] + 9 - 24 : item.endTime[3] + 9
                }시`}
              </button>
            );
          })}
          <button>추가</button>
        </div>
        {schedules && <ScheduleInfo schedules={schedules} index={sIdx} />}
      </div>
    </ModalFrame>
  );
}

function ScheduleInfo({ schedules, index }) {
  console.log(schedules[index]);

  const [startValue, setStartValue] = useState();
  const [endValue, setEndValue] = useState();
  const [capacity, setCapacity] = useState();
  const [totalCapacity, setTotalCapacity] = useState();
  const [guideLine, setGuideLine] = useState();
  const queryClient = useQueryClient();
  const { mutate } = useMutation(['updateSchedule'], data => updateVolNoticeSchedule(data), {
    onSuccess: ({ data }) => {
      if (data.message === 'SUCCESS') {
        queryClient.invalidateQueries(['detailNotice', schedules[index].noticeId]);
        toast(`${schedules[index].scheduleId}번의 일정이 수정되었습니다.`, { type: 'success' });
      } else {
        toast(data.message, { type: 'error' });
      }
    },
  });
  useEffect(() => {
    setStartValue(dayjs(convertDateToDayjs(schedules[index].startTime)).locale('Asia/Seoul'));
    setEndValue(dayjs(convertDateToDayjs(schedules[index].endTime)));
    setCapacity(schedules[index].capacity);
    setTotalCapacity(schedules[index].totalCapacity);
    setGuideLine(schedules[index].guideLine);
  }, [index]);
  const handleSubmit = e => {
    const updateData = {
      startTime: startValue.$d,
      endTime: endValue.$d,
      guideLine,
      totalCapacity,
      scheduleId: schedules[index].scheduleId,
    };
    mutate(updateData);
  };
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <div className={styles.schedule}>
        <label className={styles.start}>시작 시간</label>
        <DesktopTimePicker
          className={styles.startTime}
          label="시작 시간"
          value={startValue}
          onChange={newValue => {
            setStartValue(newValue);
          }}
          renderInput={params => <TextField {...params} />}
        />
        <label className={styles.end}>종료 시간</label>
        <DesktopTimePicker
          className={styles.endTime}
          label="종료 시간"
          value={endValue}
          onChange={newValue => {
            setEndValue(newValue);
          }}
          renderInput={params => <TextField {...params} />}
        />
        <label>현재 인원</label>
        <input type="number" onChange={e => setCapacity(e.target.value)} value={capacity} />
        <label>총 봉사 인원</label>
        <input type="number" onChange={e => setTotalCapacity(e.target.value)} value={totalCapacity} />
      </div>
      <input type="textarea" onChange={e => setGuideLine(e.target.value)} value={guideLine} />
      <button onClick={handleSubmit}>전송</button>
    </LocalizationProvider>
  );
}
const convertDateToDayjs = date => {
  const year = date[0];
  const month = date[1] < 10 ? `0${date[1]}` : date[1];
  const day = date[2] < 10 ? `0${date[2]}` : date[2];
  const hour = date[3] < 10 ? `0${date[3]}` : date[3];
  const min = date[4] < 10 ? `0${date[4]}` : date[4];
  return `${year}-${month}-${day}T${hour}:${min}:00.000Z`;
};
