import React from 'react';
import logo from 'assets/images/logo.png';
import { RiKakaoTalkLine } from 'react-icons/ri';
import { SiNaver } from 'react-icons/si';
import { GrGoogle } from 'react-icons/gr';
import PatLogo from 'assets/images/pat.png';
import styles from './Login.module.scss';

export default function Login() {
  const kauthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_REST_KEY}&redirect_uri=${process.env.REACT_APP_KAKAO_REDIRECT_URI}&response_type=code`;
  console.log(process.env.REACT_APP_KAKAO_REDIRECT_URI);
  return (
    <>
      <div className={styles['main-container']}>
        <div className={styles.header}>
          <img src={logo} alt="logo" />
          <div className={styles['header-title']}>
            <span>어쩌면 우리 가족을 바꾸게 될</span> <span>새로운 가족을 받아들이는 일</span>
          </div>
        </div>
        <button
          onClick={() => {
            window.location.href = kauthUrl;
          }}
          className={styles.button}
        >
          <RiKakaoTalkLine />
          <div>카카오로 바로시작</div>
        </button>
        <button className={styles.button}>
          <SiNaver />
          <div>네이버로 바로시작</div>
        </button>
        <button className={styles.button}>
          <GrGoogle />
          <div>구글로 바로시작</div>
        </button>
      </div>
      <img src={PatLogo} alt="" className={styles['pat-logo']} />
    </>
  );
}
