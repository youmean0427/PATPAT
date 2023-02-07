import { logout } from 'apis/utils/auth';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { isLoginState } from 'recoil/atoms/user';

export default function useAuth() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);

  const handleClickLogout = () => {
    logout();
    setIsLogin(false);
    navigate('/');
  };

  const handleClickLogin = () => {
    navigate('/login');
  };

  return [isLogin, setIsLogin, handleClickLogout, handleClickLogin];
}
