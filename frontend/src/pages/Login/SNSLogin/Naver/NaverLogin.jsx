import React, { useEffect } from 'react';
import styles from './naver.module.scss';
import NaverBtn from 'assets/images/naverBtn.png';
import { useNavigate } from 'react-router-dom';

export default function NaverLogin({ setGetToken, setUserInfo }) {
  const { naver } = window;
  const NAVER_CLIENT_ID = 'kHAzMXmKlCJ5LkjYZdSB';
  const NAVER_CALLBACK_URL = 'http://localhost:3000/login/naver';
  const navigate = useNavigate();

  const initializeNaverLogin = () => {
    const naverLogin = new naver.LoginWithNaverId({
      clientId: NAVER_CLIENT_ID,
      callbackUrl: NAVER_CALLBACK_URL,
      // 팝업창으로 로그인을 진행할 것인지 선택
      isPopup: false,
      // 버튼 타입 ( 색상, 타입, 크기 변경 가능 )
      loginButton: { color: 'green', type: 1, height: 58 },
      callbackHandle: true,
    });
    naverLogin.init();

    // 백엔드 개발자가 정보를 전달해줄 경우 아래 생략

    // naverLogin.getLoginStatus(async function (status) {
    //   if (status) {
    //     // // 선택하여 추출
    //     // const userid = naverLogin.user.getEmail();
    //     // const username = naverLogin.user.getName();

    //     // 정보 전체를 state 에 저장하여 추출
    //     setUserInfo(naverLogin.user);
    //   }
    // });
  };

  // URL 에 엑세스 토큰이 붙어서 전달
  const userAccessToken = () => {
    window.location.href.includes('access_token') && getToken();
  };

  const getToken = () => {
    const token = window.location.href.split('=')[1].split('&')[0]; // 토큰 추출
    // 이후 로컬 스토리지 또는 state에 저장
    localStorage.setItem('naver_token', token);
    navigate('/home');
  };

  const handleNaverLogin = () => {
    if (document && document?.querySelector('#naverIdLogin')?.firstChild && window !== undefined) {
      const loginBtn = document.getElementById('naverIdLogin')?.firstChild;
      loginBtn.click();
    }
  };
  useEffect(() => {
    initializeNaverLogin();
    userAccessToken();
  }, []);

  return (
    <>
      <div id="naverIdLogin" className={styles['origin-naver']}></div>
      <img
        src={NaverBtn}
        alt="네이버"
        onClick={handleNaverLogin}
        className={styles['new-naver']}
        title="네이버 로그인"
      />
    </>
  );
}
