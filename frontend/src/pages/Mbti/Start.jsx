import React from 'react';
import styles from './Start.module.scss';
import logo from 'assets/images/mbti-logo.png';
import { Link, useNavigate } from 'react-router-dom';
import MbtiContainer from 'containers/MbtiContainer';
import { useMutation, useQuery } from '@tanstack/react-query';
import { getMbtiCount, increaseMbtiCount } from 'apis/api/mbti';
export default function Start() {
  const { data, isLoading } = useQuery(['mbtiCount'], getMbtiCount);
  const navigate = useNavigate();
  const { mutate } = useMutation(['increaseMbtiCount'], () => increaseMbtiCount());
  const handleClickStart = () => {
    navigate('/mbti/test');
    mutate();
  };
  if (isLoading) return;
  return (
    <MbtiContainer>
      <img className={styles.logo} src={logo} alt="mbti" />
      <div className={styles.bottom}>
        <p className={styles.desc}>
          <span>보호소</span>에 있는 강아지 중 나와 맞는 강아지를 만나보고 좋은 가족을 만날 수 있도록 응원해주세요.
        </p>
        <button onClick={handleClickStart} className={styles['start-btn']}>
          테스트 시작하기
        </button>
        <p className={styles['total-desc']}>
          현재 까지 총 <span>{data}</span> 명이 테스트에 참여했어요!{' '}
        </p>
      </div>
    </MbtiContainer>
  );
}
