import React from 'react';
import styles from './Navbar.module.scss';
import { Link } from 'react-router-dom';
import logo from 'assets/images/logo.png';
import MenuList from './MenuList';

export default function Navbar() {
  const isLogin = localStorage.getItem('isLogin');
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
          <Link to="/logout" className={styles.login}>
            로그아웃
          </Link>
        </div>
      ) : (
        <div className={styles.right}>
          <Link to="/login" className={styles.login}>
            로그인
          </Link>
        </div>
      )}
    </>
  );
}
