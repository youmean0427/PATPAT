import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import React from 'react';
import { useState } from 'react';
import ModalFrameSmall from '../ModalFrameSmall';
import { createShelter, getAuthShelterList } from 'apis/api/shelter';
import Select from 'react-select';
import { changeShelterList } from 'utils/changeSelectTemplate';
import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './EnrollShelterModal.module.scss';
import ModalFrame from '../ModalFrame';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { toast } from 'react-toastify';
export default function EnrollShelterModal({ isOpen, handleClickModalClose }) {
  const [code, setCode] = useState();
  const [name, setName] = useState();
  const { data, isLoading } = useQuery(['authShelters'], getAuthShelterList);
  const queryClient = useQueryClient();
  const { mutate } = useMutation(['createShelter'], data => createShelter(data), {
    onSuccess: ({ data }) => {
      toast('보호소가 등록되었습니다.', { type: 'success' });
      queryClient.invalidateQueries('getUserInfo');
    },
  });

  const handleChangeOnShelter = useCallback(selected => {
    setName(selected.label);
  });
  const handleSubmit = e => {
    e.preventDefault();
    mutate({ shelterCode: code, shelterName: name });
    handleClickModalClose();
  };
  if (isLoading) return;
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={400} height={600}>
      <div className={styles.title}>보호소 등록</div>
      <form className={styles.form} onSubmit={handleSubmit}>
        <div className={styles.wrap}>
          <div className={styles['input-wrap']}>
            <label>코드</label>
            <input type="text" placeholder="코드를 입력하세요" value={code} onChange={e => setCode(e.target.value)} />
          </div>
          <div className={styles['input-wrap']}>
            <label>보호소</label>
            <Select options={changeShelterList(data)} onChange={handleChangeOnShelter} />
          </div>
        </div>
        <button className={styles.submit}>제출</button>
      </form>
    </ModalFrame>
  );
}
