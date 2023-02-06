import React from 'react';
import logo from 'assets/images/logo.png';
import naverBtn from 'assets/images/naverBtn.png';
import kakaoBtn from 'assets/images/kakaoBtn.png';
import googleBtn from 'assets/images/googleBtn.png';
import styles from './Login.module.scss';

export default function Login() {
  const kauthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_REST_KEY}&redirect_uri=${process.env.REACT_APP_KAKAO_REDIRECT_URI}&response_type=code`;
  const nauthUrl = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${process.env.REACT_APP_NAVER_REST_KEY}&redirect_uri=${process.env.REACT_APP_NAVER_REDIRECT_URI}`;
  const gauthUrl = `https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=${process.env.REACT_APP_GOOGLE_REST_KEY}&redirect_uri=${process.env.REACT_APP_GOOGLE_REDIRECT_URI}&scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email`;
  return (
    <div className={styles['main-container']}>
      <div className={styles.header}>
        <img src={logo} alt="logo" />
        <div className={styles['header-title']}>
          <span>어쩌면 우리 가족을 바꾸게 될</span> <span>새로운 가족을 받아들이는 일</span>
        </div>
      </div>
      <div className={styles['button-box']}>
        <button
          className={styles.button}
          onClick={() => {
            window.location.href = kauthUrl;
          }}
        >
          <img src={kakaoBtn} alt="카카오" />
        </button>
        <button
          className={styles.button}
          onClick={() => {
            window.location.href = nauthUrl;
          }}
        >
          <img src={naverBtn} alt="네이버" />
        </button>
        <button
          onClick={() => {
            window.location.href = gauthUrl;
          }}
          className={styles.button}
        >
          <img src={googleBtn} alt="구글" />
        </button>
      </div>
    </div>
  );
}
