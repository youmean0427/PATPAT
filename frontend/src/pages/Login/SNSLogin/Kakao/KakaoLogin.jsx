import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from 'apis/utils/auth';

export default function KakaoLogin() {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get('code');
  const getTokenFromServer = async () => {
    await login('kakao', code);
    navigate('/');
  };
  useEffect(() => {
    getTokenFromServer();
  }, []);

  return;
}
