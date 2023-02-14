import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useState } from 'react';
import ModalFrame from '../ModalFrame';
import styles from './EnrollVolunteerModal.module.scss';
import { createVolNotice, getVolNoticePerMonth } from 'apis/api/volunteer';
import { myShelterIdState } from 'recoil/atoms/user';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { volNoticeListPerMonthState } from 'recoil/atoms/shelter';
import { toast } from 'react-toastify';
export default function EnrollVolunteerModal({ date, isOpen, handleClickModalClose }) {
  const [value, setValue] = useState('');
  const myShelterId = useRecoilValue(myShelterIdState);
  const setData = useSetRecoilState(volNoticeListPerMonthState);
  const { mutate } = useMutation(['enrollVolunteerNotice'], data => createVolNotice(data), {
    onSuccess: () => {
      getVolNoticePerMonth(myShelterId, date.getFullYear(), date.getMonth() + 1).then(res => setData(res));
      handleClickModalClose();
      toast(`${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}일 공고 등록 완료`, { type: 'success' });
    },
    onError: error => {
      console.log(error);
    },
  });
  const handleChange = e => {
    setValue(e.target.value);
  };
  const handleSubmit = e => {
    e.preventDefault();
    mutate({ title: value, shelterId: myShelterId, volunteerDate: date });
  };
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={400} height={200}>
      <div className={styles.container}>
        <div className={styles.title}>봉사 공고 등록</div>
        <span className={styles.date}>{`${date.getFullYear()}-${
          date.getMonth() + 1
        }-${date.getDate()}일 공고 등록`}</span>
        <form className={styles.form} onSubmit={handleSubmit}>
          <input
            className={styles.input}
            type="text"
            placeholder="봉사 공고 제목 입력"
            value={value}
            onChange={handleChange}
          />
          <button className={styles.btn} type="submit">
            등록
          </button>
        </form>
      </div>
    </ModalFrame>
  );
}
