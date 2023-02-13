import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useState } from 'react';
import ModalFrame from '../ModalFrame';
import styles from './EnrollVolunteerModal.module.scss';
import { createVolNotice } from 'apis/api/volunteer';
import { myShelterIdState } from 'recoil/atoms/user';
import { useRecoilValue } from 'recoil';
export default function EnrollVolunteerModal({ date, isOpen, handleClickModalClose }) {
  console.log(date);
  const [value, setValue] = useState('');
  const myShelterId = useRecoilValue(myShelterIdState);
  const { mutate } = useMutation(['enrollVolunteerNotice'], data => createVolNotice(data), {
    onSuccess: data => {
      console.log(data);
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
        <form onSubmit={handleSubmit}>
          <input type="text" placeholder="봉사 공고 제목 입력" value={value} onChange={handleChange} />
          <button type="submit">등록</button>
        </form>
      </div>
    </ModalFrame>
  );
}
