import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from 'apis/utils/auth';
import { isLoginState } from 'recoil/atoms/user';
import { useSetRecoilState } from 'recoil';
import { toast } from 'react-toastify';

export default function KakaoLogin() {
  const navigate = useNavigate();
  const setIsLogin = useSetRecoilState(isLoginState);
  const code = new URL(window.location.href).searchParams.get('code');
  const getTokenFromServer = async () => {
    await login('kakao', code)
      .then(data => {
        setIsLogin(true);
        toast('로그인에 성공했습니다.', { type: 'success' });
        navigate('/');
      })
      .catch(e => {
        setIsLogin(false);
        toast('로그인에 실패했습니다.', { type: 'error' });
        navigate('/login');
      });
  };
  useEffect(() => {
    getTokenFromServer();
  }, []);

  return;
}
