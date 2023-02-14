import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from 'apis/utils/auth';
import { isLoginState } from 'recoil/atoms/user';
import { useSetRecoilState } from 'recoil';

export default function KakaoLogin() {
  const navigate = useNavigate();
  const setIsLogin = useSetRecoilState(isLoginState);
  const code = new URL(window.location.href).searchParams.get('code');
  const getTokenFromServer = async () => {
    await login('kakao', code);
    setIsLogin(true);
    navigate('/');
  };
  useEffect(() => {
    getTokenFromServer();
  }, []);

  return;
}
