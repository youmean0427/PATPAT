import React, { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { REST_API_KEY, REDIRECT_URI } from './KAuth';

export default function KakaoLogin() {
  const location = useLocation();
  const navigate = useNavigate();
  const KAKAO_CODE = location.search.split('=')[1]; // 카카오톡 인가 토큰값

  // 토큰 받아오기
  const getKakaoToken = () => {
    fetch('https://kauth.kakao.com/oauth/token', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: `grant_type=authorization_code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&code=${KAKAO_CODE}`,
    })
      .then(res => res.json())
      .then(data => {
        if (data.access_token) {
          // 로컬 스토리지에 저장
          localStorage.setItem('token', data.access_token);
          navigate('/'); // 메인페이지로 리다이렉션
        } else {
          navigate('/');
        }
      });
  };

  // 로그인 완료 후 한번만 실행
  useEffect(() => {
    if (!location.search) return;
    getKakaoToken();
  }, []);

  return <div>KAKAO</div>;
}
