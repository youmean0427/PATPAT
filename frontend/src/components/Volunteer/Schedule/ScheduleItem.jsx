import { useMutation, useQuery } from '@tanstack/react-query';
import { applyVolReservation, getVolReservationOfUserCheck, getVolReservationOfUserDetail } from 'apis/api/volunteer';
import React from 'react';
import { useState } from 'react';
import styles from './ScheduleItem.module.scss';
export default function ScheduleItem({ item, open, index }) {
  const [people, setPeople] = useState(1);
  // item is scheduleId

  // useQuery ::User의 봉사 예약 신청 내역 리스트

  // const { data } = useQuery({
  //   queryKey: ['getVolReservationOfUserCheck'],
  //   queryFn: () => getVolReservationOfUserCheck(33),
  // });

  //useQuery :: 봉사 상세

  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', item],
    queryFn: () => getVolReservationOfUserDetail(item),
  });

  console.log('data', data);
  // userId :: 유저 아이디와 이름 가져오기
  let user = 'user';
  if (localStorage.getItem('user')) {
    user = JSON.parse(localStorage.getItem('user'));
  }

  // datas :: 봉사 신청할 때 필요한 Data
  const datas = {
    capacity: parseInt(people),
    endTime: '',
    reservationId: 0,
    scheduleId: 4,
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

  // if (isLoading) return;
  return (
    <div>
      {/* {index === open.index ? (
        <div>
          <div className={styles.info}>
            <div>
              <span>{data.capacity} /</span>
              <span> {data.totalCapacity}</span>
            </div>
            <div>{data.reservationState}</div>
          </div>
          <div className={styles.time}>
            <span>
              {data.startTime[0]}-{data.startTime[1] < 10 ? `0${data.startTime[1]}` : data.startTime[1]}-
              {data.startTime[2] < 10 ? `0${data.startTime[2]}` : data.startTime[2]}
            </span>
            <span>
              {data.startTime[3] < 10 ? `0${data.startTime[3]}` : data.startTime[3]}:
              {data.startTime[4] < 10 ? `0${data.startTime[4]}` : data.startTime[4]}
            </span>
            <span>~</span>
            <span>
              {data.endTime[0]}-{data.endTime[1] < 10 ? `0${data.endTime[1]}` : data.endTime[1]}-
              {data.endTime[2] < 10 ? `0${data.endTime[2]}` : data.endTime[2]}
            </span>
            <span>
              {data.endTime[3] < 10 ? `0${data.endTime[3]}` : data.endTime[3]}:
              {data.endTime[4] < 10 ? `0${data.endTime[4]}` : data.endTime[4]}
            </span>
          </div>

          <div>{data.guideLine}</div>
          <div className={styles.peopleButton}>
            {parseInt(data.capacity) === parseInt(data.totalCapacity) ? (
              '모든 인원이 모집되었습니다.'
            ) : (
              <div>
                <input
                  className={styles.input}
                  min="0"
                  type="text"
                  placeholder="인원"
                  onChange={e => {
                    if (parseInt(e.target.value) + parseInt(data.capacity) > parseInt(data.totalCapacity)) {
                      alert('인원수를 확인해주세요');
                    } else if (parseInt(e.target.value) < 0) {
                      alert('인원수를 확인해주세요');
                      setPeople(1);
                    } else {
                      setPeople(e.target.value);
                    }
                  }}
                />

                <button
                  className={styles.button}
                  onClick={() => {
                    if (data.capacity !== data.totalCapacity) {
                      mutation();
                      alert('봉사가 신청되었습니다.');
                      console.log(people);
                    } else {
                      alert('모든 인원이 모집되었습니다.');
                    }
                  }}
                >
                  신청
                </button>
              </div>
            )}
          </div>
        </div>
      ) : null} */}
    </div>
  );
}
