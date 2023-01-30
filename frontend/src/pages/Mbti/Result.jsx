import { useQuery } from '@tanstack/react-query';
import MbtiContainer from 'components/Common/MbtiContainer';
import kakao from 'assets/images/kakaoBtn.png';
import copyLink from 'assets/images/link.png';
import facebook from 'assets/images/facebook.png';
import styles from './Result.module.scss';
import React from 'react';
import { useLocation } from 'react-router';
import { Link } from 'react-router-dom';
import { getMbtiBreedInfo } from 'apis/api/shelter';

export default function Result() {
  const { state } = useLocation();
  const { data, isLoading } = useQuery(['mbtiResultList'], () => getMbtiBreedInfo(state.mbti));
  if (isLoading) return;
  const { mbti, breed, feature, desc, imgUrl } = data;
  return (
    <MbtiContainer>
      <div className={styles['main-result']}>
        <div className={styles.feature}>
          🐶 <span>{feature}</span> 🐶
        </div>
        <div className={styles['img-box']}>
          <img src={imgUrl} alt="img" />
        </div>
        <div className={styles.result}>
          <span>{mbti}</span>
          <span>{breed}</span>
        </div>
      </div>
      <div className={styles['desc-box']}>
        <span>{desc}</span>
        <div className={styles['btn-box']}>
          <Link className={styles.link} to="#">
            <span className={styles.highlight}>PATPAT</span>에서 나의 가족 찾아보기
          </Link>
        </div>
      </div>
      <button className={styles.retry}>테스트 다시하기</button>
      <p className={styles['share-desc']}>
        오늘 테스트 결과를 재밌게 읽으셨나요? 유기견 그리고 <span>PATPAT</span>에 대해 더 많은 분이 아실 수 있도록, 이
        <span>MBTI 테스트를 주변 친구들에게 공유</span>해주세요~
      </p>
      <div className={styles['share-box']}>
        <span>공유하기</span>
        <div className={styles['share-list']}>
          <img src={kakao} alt="kakao" />
          <img src={facebook} alt="facebook" />
          <img src={copyLink} alt="url" />
        </div>
      </div>
    </MbtiContainer>
  );
}
