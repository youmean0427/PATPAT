import React from 'react';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import styles from './ExcelForm.module.scss';
import { AiFillFileAdd } from 'react-icons/ai';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { enrollProtectByExcel } from 'apis/api/protect';
import { useNavigate } from 'react-router-dom';
export default function ExcelForm() {
  const myShelterId = useRecoilValue(myShelterIdState);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    mode: 'onChange',
  });
  const [filePath, setFilePath] = useState('파일을 등록해주세요.');
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const { mutate } = useMutation(['enrollProtectByExcel'], formData => enrollProtectByExcel(formData), {
    onSuccess: data => {
      navigate(`/shelter/${myShelterId}/protect`, { state: { shelterId: myShelterId } });
    },
    onError: error => {
      console.log(error);
    },
  });
  const onSubmit = data => {
    const formData = new FormData();
    console.log(data.excelFile[0]);
    formData.append('uploadFile', data.excelFile[0]);
    formData.append('shelterId', myShelterId);
    mutate(formData);
  };

  const handleChange = e => {
    if (e.target.value === '') {
      setFilePath('파일을 등록해주세요.');
    } else {
      setFilePath(e.target.value);
    }
  };
  return (
    <form onSubmit={handleSubmit(onSubmit)} className={styles['input-box']}>
      <label htmlFor="input-item">
        <AiFillFileAdd />
      </label>
      <input
        id="input-item"
        className={styles.input}
        type="file"
        accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        {...register('excelFile', { onChange: handleChange })}
      />
      <span className={styles['input-path']}>{filePath}</span>
      <button type="submit">데이터 전송</button>
    </form>
  );
}
