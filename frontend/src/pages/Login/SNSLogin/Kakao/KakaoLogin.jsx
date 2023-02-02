import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function KakaoLogin() {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get('code');
  const getTokenFromServer = async code => {
    try {
      const res = await axios.get(`http://i8e104.p.ssafy.io:8081/user/login/kakao?code=${code}`);
      // token이랑 user 정보 받으면 token은 localStorage에 user 정보는 recoil에 ☆★☆★☆★ 저 ~ 장 ☆★☆★☆★
      const accessToken = res.data.tokenDto.accessToken;
      const refreshToken = res.data.tokenDto.refreshToken;
      const userInfo = res.data.userDto;

      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('isLogin', true);
      localStorage.setItem('userProfile', userInfo);
      navigate('/');
    } catch (e) {
      console.log(e);
    }
  };
  useEffect(() => {
    getTokenFromServer(code);
  }, []);

  return;
}
