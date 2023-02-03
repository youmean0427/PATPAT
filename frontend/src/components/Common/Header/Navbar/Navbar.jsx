import React, { useEffect } from 'react';
import styles from './Navbar.module.scss';
import { Link, useNavigate } from 'react-router-dom';
import logo from 'assets/images/logo.png';
import MenuList from './MenuList';
import { logout } from 'apis/utils/auth';
import { useRecoilState } from 'recoil';
import { isLoginState } from 'recoil/atoms/user';

export default function Navbar() {
  const navigator = useNavigate();
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  useEffect(() => {
    if (localStorage.getItem('isLogin')) {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
  }, []);
  return (
    <>
      <div className={styles.left}>
        <Link className={styles['logo-link']} to="/">
          <div className={styles['logo-box']}>
            <img src={logo} alt="logo" />
          </div>
        </Link>
        <MenuList />
      </div>
      {isLogin ? (
        <div className={styles.right}>
          <div
            onClick={() => {
              logout();
              setIsLogin(false);
            }}
            className={styles.login}
          >
            로그아웃
          </div>
        </div>
      ) : (
        <div className={styles.right}>
          <div onClick={() => navigator('/login')} className={styles.login}>
            로그인
          </div>
        </div>
      )}
    </>
  );
}
