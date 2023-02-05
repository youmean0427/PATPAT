import { useQuery } from '@tanstack/react-query';
import MbtiContainer from 'containers/MbtiContainer';
import kakao from 'assets/images/kakaoBtn.png';
import copyLink from 'assets/images/link.png';
import styles from './Result.module.scss';
import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router';
import { getMbtiBreedInfo } from 'apis/api/shelter';
import { shareKakao } from 'utils/shareKakaoLink';

export default function Result() {
  const { state } = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
    script.async = true;
    document.body.appendChild(script);
    return () => document.body.removeChild(script);
  }, []);

  const { data, isLoading } = useQuery(['mbtiResultList'], () => getMbtiBreedInfo(state.mbti));
  if (isLoading) return;
  console.log(data);
  const { breedId, breedName, title, description, thumbnail } = data;
  return (
    <MbtiContainer>
      <div className={styles['main-result']}>
        <div className={styles.title}>
          🐶 <span>{title}</span> 🐶
        </div>
        <div className={styles['img-box']}>
          <img src={thumbnail ? thumbnail : copyLink} alt="img" />
        </div>
        <div className={styles.result}>
          <span>{state.mbti}</span>
          <span>{breedName}</span>
        </div>
      </div>
      <div className={styles['desc-box']}>
        <span>{description}</span>
        <div className={styles['btn-box']}>
          <button
            onClick={() => {
              window.scrollTo(0, 0);
              navigate('/mbti/result/map', { state: { breedId, breedName, thumbnail } });
            }}
            className={styles.link}
          >
            <span className={styles.highlight}>PATPAT</span>에서 나의 가족 찾아보기
          </button>
        </div>
      </div>
      <button
        onClick={() => {
          navigate('/mbti/test');
        }}
        className={styles.retry}
      >
        테스트 다시하기
      </button>
      <p className={styles['share-desc']}>
        오늘 테스트 결과를 재밌게 읽으셨나요? 유기견 그리고 <span>PATPAT</span>에 대해 더 많은 분이 아실 수 있도록, 이
        <span> MBTI 테스트를 주변 친구들에게 공유</span>해주세요~
      </p>
      <div className={styles['share-box']}>
        <span>공유하기</span>
        <div className={styles['share-list']}>
          <button
            onClick={() => {
              shareKakao(
                `${process.env.REACT_APP_PROD_CLIENT_URL}/mbti`,
                "어쩌면 우리의 가족을 바꾸는\n'PATPAT'에서 새로운 가족을 만나보세요.",
                'PATPAT 토닥토닥\n나는 어떤 강아지일까?',
                'https://ifh.cc/g/6V6qsV.png'
              );
            }}
          >
            <img src={kakao} alt="kakao" />
          </button>
          <button
            onClick={() => {
              navigator.clipboard.writeText(`${process.env.REACT_APP_PROD_CLIENT_URL}/mbti`);
            }}
          >
            <img src={copyLink} alt="url" />
          </button>
        </div>
      </div>
    </MbtiContainer>
  );
}
