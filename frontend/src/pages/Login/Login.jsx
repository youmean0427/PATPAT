import React from 'react';
import LoginNavigation from './LoginNavigation';
import NaverLogin from './SNSLogin/Naver/NaverLogin';
import KakaoLoginBtn from 'assets/images/kakaoBtn.png';
import GoogleLoginBtn from 'assets/images/googleBtn.png';
import PatLogo from 'assets/images/pat.png';
import styles from './login.module.scss';
import { REST_API_KEY, REDIRECT_URI } from './SNSLogin/Kakao/KAuth';

export default function Login() {
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  return (
    <>
      <LoginNavigation />
      <div className={styles['main-container']}>
        <div className={styles.header}>
          <h1 className={styles['main-h1']}>로그인</h1>
          <p>PATPAT에 방문해주셔서 감사합니다.</p>
        </div>
        <hr className={styles.hr} />
        <div className={styles['sub-container']}>
          <h1 className={styles['sub-h1']}>SNS 로그인</h1>
          <div className={styles.functions}>
            <NaverLogin />
            <a href={KAKAO_AUTH_URL}>
              <img src={KakaoLoginBtn} alt="카카오" title="카카오 로그인" />
            </a>
            <a href="">
              <img src={GoogleLoginBtn} alt="구글" title="구글 로그인" />
            </a>
          </div>
        </div>
      </div>
      <img src={PatLogo} alt="" className={styles['pat-logo']} />
    </>
  );
}
