import { useMutation, useQuery } from '@tanstack/react-query';
import {
  applyVolReservation,
  getVolReservationOfUser,
  getVolReservationOfUserCheck,
  getVolReservationOfUserDetail,
} from 'apis/api/volunteer';
import HTMLReactParser from 'html-react-parser';
import React from 'react';
import { useState } from 'react';
import { toast } from 'react-toastify';
import styles from './ScheduleItem.module.scss';
import { isLoginState } from 'recoil/atoms/user';
import { useRecoilState } from 'recoil';
import UserBadge from './UserBadge';
import { Link } from 'react-router-dom';

export default function ScheduleItem({ item, open, index, noticeId, volunteerDate }) {
  // *** item is scheduleId

  const [isLogin] = useRecoilState(isLoginState);
  const [people, setPeople] = useState(0);
  const [submit, setSubmit] = useState(0);

  // useQuery :: User의 봉사 예약 신청 내역 리스트
  const { data: checkData } = useQuery({
    queryKey: ['getVolReservationOfUserCheck'],
    queryFn: () => getVolReservationOfUserCheck(noticeId),
  });

  // useQuery :: 봉사 상세
  const { data, isLoading } = useQuery({
    queryKey: ['getVolNoticePerMonth', item],
    queryFn: () => getVolReservationOfUserDetail(item),
  });

  const { data: userVolList, error } = useQuery({
    queryKey: ['getVolReservationOfUser'],
    queryFn: () => getVolReservationOfUser(10, 0),
    enabled: isLogin,
  });

  // userId :: 유저 아이디와 이름 가져오기
  let user = 'user';
  if (localStorage.getItem('user')) {
    user = JSON.parse(localStorage.getItem('user'));
  }

  //
  const Check = () => {
    let result = false;
    for (let i = 0; i < checkData.length; i++) {
      if (checkData[i].scheduleId === item) {
        result = checkData[i].isOk;
      }
    }

    return result;
  };

  const NowStateCode = () => {
    let result = 0;
    if (userVolList) {
      for (let i = 0; i < userVolList.list.length; i++) {
        if (userVolList.list[i].scheduleId === item) {
          result = userVolList.list[i].reservationStateCode;
        }
      }
    }
    return result;
  };

  const NowState = () => {
    let result = '';
    if (userVolList) {
      for (let i = 0; i < userVolList.list.length; i++) {
        if (userVolList.list[i].scheduleId === item) {
          result = userVolList.list[i].reservationState;
        }
      }
    }
    return result;
  };

  // datas :: 봉사 신청할 때 필요한 Data
  // useMutation :: 봉사 신청
  const { mutate: mutation } = useMutation(['applyVolReservation'], () => {
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
      volunteerDate: volunteerDate,
    };
    return applyVolReservation(datas);
  });
  if (error) return;
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
              <div className={styles.capacity}>
                <span>{data.capacity === null ? 0 : data.capacity}</span>
                <span> / </span>
                <span>{data.totalCapacity}</span>
              </div>
            </div>
          </div>
          <div className={styles.time}></div>
          <hr />
          <div className={styles.guideLine}>{HTMLReactParser(data.guideLine)}</div>
          <hr />
          <div className={styles.state}>
            {isLogin === true ? (
              data.capacity !== data.totalCapacity ? (
                Check() !== false ? (
                  submit !== 1 ? (
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
                              toast('봉사가 신청되었습니다.', { type: 'success' });
                              setSubmit(1);
                            } else {
                              setPeople('');
                              toast('인원을 확인해주세요.', { type: 'warning' });
                            }
                          }}
                        >
                          신청
                        </button>
                      </div>
                    </div>
                  ) : (
                    <div>
                      {NowState() !== '' ? (
                        <Link to="/mypage/volunteer">
                          <UserBadge
                            className={styles.userBadge}
                            state={NowState()}
                            stateCode={NowStateCode()}
                            items={null}
                          />
                        </Link>
                      ) : (
                        <Link to="/mypage/volunteer">
                          <UserBadge className={styles.userBadge} state="대기중" stateCode={0} items={null} />
                        </Link>
                      )}
                    </div>
                  )
                ) : (
                  <div>
                    {NowState() !== '' ? (
                      <Link to="/mypage/volunteer">
                        <UserBadge
                          className={styles.userBadge}
                          state={NowState()}
                          stateCode={NowStateCode()}
                          items={null}
                        />
                      </Link>
                    ) : null}
                  </div>
                )
              ) : (
                <div>
                  {NowState() !== '' ? (
                    <Link to="/mypage/volunteer">
                      <UserBadge
                        className={styles.userBadge}
                        state={NowState()}
                        stateCode={NowStateCode()}
                        items={null}
                      />
                    </Link>
                  ) : (
                    <div className={styles.full}>인원초과</div>
                  )}
                </div>
              )
            ) : (
              <div className={styles.message}>봉사활동 신청은 로그인이 필요합니다.</div>
            )}
          </div>
        </div>
      ) : null}
    </div>
  );
}
