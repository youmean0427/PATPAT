import { Margin } from '@mui/icons-material';
import styledEngine from '@mui/styled-engine';
import { useMutation, useQuery } from '@tanstack/react-query';
import { applyVolReservation, getVolReservationOfUserCheck, getVolReservationOfUserDetail } from 'apis/api/volunteer';
import HTMLReactParser from 'html-react-parser';
import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import styles from './ScheduleItem.module.scss';
export default function ScheduleItem({ item, open, index, noticeId }) {
  // item is scheduleId

  // useState

  const [people, setPeople] = useState(0);
  const [submit, setSubmit] = useState(0);
  // useQuery :: User의 봉사 예약 신청 내역 리스트
  const { data: checkData } = useQuery({
    queryKey: ['getVolReservationOfUserCheck'],
    queryFn: () => getVolReservationOfUserCheck(noticeId),
  });

  // useQuery :: 봉사 상세

  const { data, isLoading, refetch } = useQuery({
    queryKey: ['getVolNoticePerMonth', item],
    queryFn: () => getVolReservationOfUserDetail(item),
  });

  //
  // userId :: 유저 아이디와 이름 가져오기
  let user = 'user';
  if (localStorage.getItem('user')) {
    user = JSON.parse(localStorage.getItem('user'));
  }

  const Check = () => {
    let result = false;
    for (let i = 0; i < checkData.length; i++) {
      if (checkData[i].scheduleId === item) {
        result = checkData[i].isOk;
      }
    }

    return result;
  };

  // datas :: 봉사 신청할 때 필요한 Data
  const datas = {
    capacity: parseInt(people),
    endTime: '',
    reservationId: 0,
    scheduleId: item,
    shelterName: '',
    startTime: '',
    stateCode: 0,
    userId: user.userId,
    userName: user.userName,
    volunteerDate: '',
  };

  // useMutation :: 봉사 신청
  const { mutate: mutation } = useMutation(['applyVolReservation'], () => {
    return applyVolReservation(datas);
  });

  useEffect(() => {
    refetch();
  }, [data]);
  if (isLoading) return;

  return (
    <div>
      {index === open.index ? (
        <div>
          <div className={styles.containerTime}>
            <div>
              <span>시작시간</span>
              <div>{data.startTime}</div>
            </div>
            <div>
              <span>종료시간</span>
              <div>{data.endTime}</div>
            </div>
            <div>
              <span>봉사인원</span>
              <div>
                <span>{data.capacity === null ? 0 : data.capacity}</span>
                <span> / </span>
                {data.totalCapacity}
              </div>
            </div>
          </div>
          <div className={styles.time}></div>
          <hr />
          <div className={styles.guideLine}>{HTMLReactParser(data.guideLine)}</div>
          <hr />
          <div>
            <div className={styles.peopleButton}>
              <span className={styles.people}>신청인원</span>
              <div className={styles.input}>
                <input
                  min="0"
                  type="number"
                  placeholder="인원"
                  onChange={e => {
                    setPeople(e.target.value);
                  }}
                  value={people}
                />
              </div>

              <div className={styles.button}>
                <button
                  onClick={() => {
                    if (people > 0 && people <= data.totalCapacity - data.capacity) {
                      mutation();
                      alert('봉사가 신청되었습니다.');
                      setSubmit(1);
                    } else {
                      setPeople('');
                      alert('인원을 확인해주세요.');
                    }
                  }}
                >
                  신청
                </button>
              </div>
            </div>

            <div className={styles.requested}>
              <div>이미 신청한 봉사활동 입니다.</div>
            </div>
          </div>
        </div>
      ) : null}
    </div>
  );
}
