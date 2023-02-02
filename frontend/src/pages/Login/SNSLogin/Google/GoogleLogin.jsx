import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from 'apis/utils/auth';

export default function GoogleLogin() {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get('code');
  const getTokenFromServer = async () => {
    await login('google', code);
    navigate('/');
  };
  useEffect(() => {
    getTokenFromServer();
  }, []);

  return;
}
