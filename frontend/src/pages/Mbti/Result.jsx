import { useQuery } from '@tanstack/react-query';
import MbtiContainer from 'components/Common/MbtiContainer';
import logo from 'assets/images/mbti-logo.png';
import styles from './Result.module.scss';
import React from 'react';
import { useLocation } from 'react-router';
import { Link } from 'react-router-dom';
import { getMbtiResult } from 'apis/api/mbti';

export default function Result() {
  const { state } = useLocation();
  const { data, isLoading } = useQuery(['mbtiResultList'], () => getMbtiResult(state.mbti));
  if (isLoading) return;
  const { id, mbti, breed, feature, desc, imgUrl } = data;
  return (
    <MbtiContainer>
      <img className={styles.logo} src={logo} alt="mbti" />
      <span className={styles.title}>나는 어떤 강아지 일까?</span>
      <span className={styles.feature}>{feature}</span>
      <div className={styles.result}>
        <span>{mbti}</span>
        <span>{breed}</span>
      </div>
      <div className={styles['img-box']}>
        <img src={imgUrl} alt="img" />
      </div>
      <div className={styles['btn-box']}>
        <Link to="#">나의 가족 만나러가기</Link>
      </div>
      <div className={styles['desc-box']}>{desc}</div>
    </MbtiContainer>
  );
}
