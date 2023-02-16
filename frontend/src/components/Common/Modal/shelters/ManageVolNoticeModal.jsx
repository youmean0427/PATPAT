import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import {
  deleteVolSchedule,
  getVolNoticeInfoPerDay,
  updateVolNoticeSchedule,
  createVolNoticeSchedule,
  changeReservationState,
} from 'apis/api/volunteer';
import React, { useEffect } from 'react';
import { useState } from 'react';
import ModalFrame from '../ModalFrame';
import styles from './ManageVolNoticeModal.module.scss';
import { toast } from 'react-toastify';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

import { AiOutlinePlusCircle } from 'react-icons/ai';
import 'components/Report/Update/MissingDog/ckeditor.scss';
import useModal from 'hooks/useModal';
import NoticeDeleteAlertModal from '../Alert/NoticeDeleteAlertModal';

export default function ManageVolNoticeModal({
  setClick,
  noticeId,
  isOpen: isOpen2,
  handleClickModalClose: closeModal,
}) {
  const [sIdx, setSIdx] = useState(0);
  const [isAddBtn, setIsAddBtn] = useState(false);
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  const { data, isLoading } = useQuery(['detailNotice', noticeId], () => getVolNoticeInfoPerDay(noticeId, 50, 0));
  const buttonStyle = (index, sIdx, item) => {
    if (index === sIdx) {
      if (item.reservationStateCode === 1) {
        return `${styles.btn} ${styles.click} ${styles.magam}`;
      } else {
        return `${styles.btn} ${styles.click}`;
      }
    } else {
      if (item.reservationStateCode === 1) {
        return `${styles.btn} ${styles.magam}`;
      } else {
        return `${styles.btn}`;
      }
    }
  };
  if (isLoading) return;
  const { noticeId: id, title, schedules, volunteerDate } = data;
  return (
    <ModalFrame isOpen={isOpen2} handleClickModalClose={closeModal} width={800} height={700}>
      <div className={styles.container}>
        <div className={styles.title}>
          공고 관리
          <button onClick={handleClickModalOpen} className={styles['delete-notice']}>
            공고 삭제
          </button>
        </div>

        <div className={styles.notice}>
          <span>공고 제목</span>
          <span>{title}</span>
          <span>날짜</span>
          <span>{volunteerDate}</span>
        </div>
        <div className={styles['btn-wrap']}>
          {schedules?.map((item, index) => {
            return (
              <button
                className={buttonStyle(index, sIdx, item)}
                onClick={() => {
                  setSIdx(index);
                  setIsAddBtn(false);
                }}
                key={index}
              >
                <span className={item.reservationStateCode === 1 ? `${styles.magam} ${styles.text}` : styles.text}>
                  {item.startTime} ~ {item.endTime} ({item.capacity || 0}/{item.totalCapacity})
                </span>
              </button>
            );
          })}
          <button
            onClick={() => {
              document.querySelector(`.${styles.btn}.${styles.click}`)?.classList.remove(styles.click);
              setIsAddBtn(true);
            }}
          >
            <AiOutlinePlusCircle className={isAddBtn ? `${styles.add} ${styles.click}` : styles.add} />
          </button>
        </div>
        {schedules && (
          <ScheduleInfo
            schedules={schedules}
            index={sIdx}
            isAddBtn={isAddBtn}
            noticeId={id}
            setSIdx={setSIdx}
            setIsAddBtn={setIsAddBtn}
          />
        )}
        {!schedules && <EnrollSchedule noticeId={id} setSIdx={setSIdx} />}
      </div>
      {isOpen && (
        <NoticeDeleteAlertModal
          setClick={setClick}
          isOpen={isOpen}
          handleClickModalClose={handleClickModalClose}
          handleClickModalClose2={closeModal}
          noticeId={id}
        />
      )}
    </ModalFrame>
  );
}
function EnrollSchedule({ noticeId, setSIdx }) {
  const [startTime, setStartTime] = useState('10:00');
  const [endTime, setEndTime] = useState('11:00');
  const [totalCapacity, setTotalCapacity] = useState(0);
  const [guideLine, setGuideLine] = useState(
    '<p>봉사 공고 가이드 템플릿 예시 입니다.</p><p>&nbsp;</p><p>시작 시간 : 00 : 00&nbsp;</p><p>종료 시간 : 00 : 00&nbsp;</p><p>&nbsp;</p><h3>해야할 일&nbsp;</h3><p>봉사 진행 순서를 적어주시면 됩니다.</p><p>&nbsp;</p><h3>주의 할 점&nbsp;</h3><p>봉사 진행하면서 주의할 점을 적어주시면 됩니다.</p><p>&nbsp;</p>'
  );
  const queryClient = useQueryClient();
  const { mutate } = useMutation(['createSchedule'], data => createVolNoticeSchedule(data), {
    onSuccess: ({ data }) => {
      if (data.message === 'SUCCESS') {
        queryClient.invalidateQueries(['detailNotice', noticeId]);
        setSIdx(0);
        toast('일정이 등록되었습니다.', { type: 'success' });
      } else {
        toast(data.message, { type: 'error' });
      }
    },
  });
  const handleSubmit = e => {
    const createData = {
      startTime,
      endTime,
      guideLine,
      totalCapacity,
      noticeId,
    };
    mutate(createData);
  };
  return (
    <>
      <div className={styles.schedule}>
        <div>
          <label className={styles.start}>시작 시간</label>
          <input
            className={styles.startTime}
            type="time"
            value={startTime || '00:00'}
            onChange={e => setStartTime(e.target.value)}
          />
        </div>
        <div>
          <label className={styles.end}>종료 시간</label>
          <input
            className={styles.endTime}
            type="time"
            value={endTime || '00:00'}
            onChange={e => setEndTime(e.target.value)}
          />
        </div>
        <div>
          <label>총 봉사 인원</label>
          <input
            className={styles.total}
            type="number"
            onChange={e => setTotalCapacity(e.target.value)}
            value={totalCapacity}
          />
        </div>
      </div>
      <CKEditor
        editor={ClassicEditor}
        data={guideLine || ''}
        onChange={(event, editor) => {
          const data = editor.getData();
          setGuideLine(data);
        }}
      />
      <button className={styles.enroll} onClick={handleSubmit}>
        일정 등록
      </button>
    </>
  );
}

function ScheduleInfo({ schedules, index, isAddBtn, noticeId, setSIdx }) {
  const [startTime, setStartTime] = useState();
  const [endTime, setEndTime] = useState();
  const [capacity, setCapacity] = useState();
  const [totalCapacity, setTotalCapacity] = useState();
  const [guideLine, setGuideLine] = useState();
  const [click, setClick] = useState(0);
  const queryClient = useQueryClient();
  const { mutate } = useMutation(['updateSchedule'], data => updateVolNoticeSchedule(data), {
    onSuccess: ({ data }) => {
      if (data.message === 'SUCCESS') {
        queryClient.invalidateQueries(['detailNotice', noticeId]);
        toast(`${schedules[index].scheduleId}번의 일정이 수정되었습니다.`, { type: 'success' });
      } else {
        toast(data.message, { type: 'error' });
      }
    },
  });
  const { mutate: enrollMutate } = useMutation(['createSchedule'], data => createVolNoticeSchedule(data), {
    onSuccess: ({ data }) => {
      if (data.code) {
        toast(data.message, { type: 'error' });
      } else {
        queryClient.invalidateQueries(['detailNotice', noticeId]);
        toast('일정이 등록되었습니다.', { type: 'success' });
      }
    },
  });
  const { mutate: deleteMutate } = useMutation(
    ['deleteSchedule'],
    () => deleteVolSchedule(schedules[index].scheduleId),
    {
      onSuccess: ({ data }) => {
        queryClient.invalidateQueries(['detailNotice', noticeId]);
        if (index !== 0) {
          setSIdx(index - 1);
        }
        toast('해당 일정이 성공적으로 삭제 되었습니다.', { type: 'success' });
      },
      onError: error => {
        toast('해당 일정을 삭제 하지 못했습니다.', { type: 'error' });
      },
    }
  );
  const { mutate: changeReservationMutate } = useMutation(
    ['changeReservationState'],
    ({ userId, reservationId, stateCode }) => changeReservationState(userId, reservationId, stateCode),
    {
      onSuccess: ({ data }) => {
        if (data.message === 'SUCCESS') {
          queryClient.invalidateQueries(['detailNotice', noticeId]);
          toast(data.comment, { type: 'success' });
        } else if (data.code === '010') {
          toast(data.message, { type: 'error' });
        }
      },
    }
  );
  useEffect(() => {
    if (isAddBtn) {
      setStartTime('09:00');
      setEndTime('10:00');
      setCapacity(0);
      setTotalCapacity(0);
      setGuideLine(
        '<p>봉사 공고 가이드 템플릿 예시 입니다.</p><p>&nbsp;</p><p>시작 시간 : 00 : 00&nbsp;</p><p>종료 시간 : 00 : 00&nbsp;</p><p>&nbsp;</p><h3>해야할 일&nbsp;</h3><p>봉사 진행 순서를 적어주시면 됩니다.</p><p>&nbsp;</p><h3>주의 할 점&nbsp;</h3><p>봉사 진행하면서 주의할 점을 적어주시면 됩니다.</p><p>&nbsp;</p>'
      );
    } else {
      setStartTime(schedules[index].startTime);
      setEndTime(schedules[index].endTime);
      setCapacity(schedules[index].capacity);
      setTotalCapacity(schedules[index].totalCapacity);
      setGuideLine(schedules[index].guideLine);
    }
  }, [index, setCapacity, setTotalCapacity, setGuideLine, schedules, isAddBtn]);
  const handleSubmit = e => {
    const updateData = {
      startTime,
      endTime,
      guideLine,
      totalCapacity,
      scheduleId: schedules[index].scheduleId,
    };
    const createData = {
      startTime,
      endTime,
      guideLine,
      totalCapacity,
      noticeId,
    };
    if (isAddBtn) {
      enrollMutate(createData);
    } else {
      mutate(updateData);
    }
  };
  return (
    <>
      <div>
        <label className={styles.capacity}>
          현재 인원 <span>{capacity || 0}</span>
        </label>
      </div>
      <div className={styles.schedule}>
        <div>
          <label className={styles.start}>시작 시간</label>
          <input
            className={styles.startTime}
            type="time"
            value={startTime || '00:00'}
            onChange={e => setStartTime(e.target.value)}
          />
        </div>
        <div>
          <label className={styles.end}>종료 시간</label>
          <input
            className={styles.endTime}
            type="time"
            value={endTime || '00:00'}
            onChange={e => setEndTime(e.target.value)}
          />
        </div>
        <div>
          <label>총 봉사 인원</label>
          <input
            className={styles.total}
            type="number"
            onChange={e => setTotalCapacity(e.target.value)}
            value={totalCapacity}
          />
        </div>
      </div>
      <CKEditor
        editor={ClassicEditor}
        data={guideLine || ''}
        onChange={(event, editor) => {
          const data = editor.getData();
          setGuideLine(data);
        }}
      />
      {isAddBtn ? (
        <button className={styles.enroll} onClick={handleSubmit}>
          일정 등록
        </button>
      ) : (
        <div className={styles['edit-btn-wrap']}>
          <button className={styles.update} onClick={handleSubmit}>
            일정 수정
          </button>
          <button onClick={deleteMutate} className={styles.delete}>
            일정 삭제
          </button>
        </div>
      )}
      <div className={styles['reservation-title']}>신청서 내역</div>
      <div className={styles.category}>
        <button
          className={click === 0 ? `${styles['category-btn']} ${styles.click}` : styles['category-btn']}
          onClick={() => setClick(0)}
        >
          신청 대기
        </button>
        <button
          className={click === 1 ? `${styles['category-btn']} ${styles.click}` : styles['category-btn']}
          onClick={() => setClick(1)}
        >
          승인
        </button>
        <button
          className={click === 4 ? `${styles['category-btn']} ${styles.click}` : styles['category-btn']}
          onClick={() => setClick(4)}
        >
          완료
        </button>
        <button
          className={click === 3 ? `${styles['category-btn']} ${styles.click}` : styles['category-btn']}
          onClick={() => setClick(3)}
        >
          불참
        </button>
      </div>
      <div className={styles.reservations}>
        {filterReservationList(schedules[index]?.reservations?.list, click).map(item => {
          const { capacity, reservationId, state, stateCode, userExp, userId, userName, userProfile } = item;
          return (
            <div
              className={
                stateCode === 1
                  ? `${styles.reservation} ${styles.approve}`
                  : stateCode === 4
                  ? `${styles.reservation} ${styles.complete}`
                  : stateCode === 3
                  ? `${styles.reservation} ${styles.noshow}`
                  : styles.reservation
              }
              key={reservationId}
            >
              <div>
                <img src={userProfile} alt="유저 이미지" />
                <div className={styles.userName}>{userName}</div>
              </div>
              <div>
                <div className={styles.rCapacity}>신청 인원 {capacity}</div>
                {stateCode === 0 && (
                  <>
                    <button
                      onClick={() => {
                        changeReservationMutate({ userId, reservationId, stateCode: 1 });
                      }}
                    >
                      승인
                    </button>
                    <button
                      onClick={() => {
                        changeReservationMutate({ userId, reservationId, stateCode: 2 });
                      }}
                    >
                      거절
                    </button>
                  </>
                )}
                {stateCode === 1 && (
                  <>
                    <button
                      onClick={() => {
                        changeReservationMutate({ userId, reservationId, stateCode: 4 });
                      }}
                    >
                      완료
                    </button>
                    <button
                      onClick={() => {
                        changeReservationMutate({ userId, reservationId, stateCode: 3 });
                      }}
                    >
                      불참
                    </button>
                  </>
                )}
              </div>
            </div>
          );
        })}
      </div>
    </>
  );
}

function filterReservationList(list, state) {
  return list.filter(item => item.stateCode === state);
}
