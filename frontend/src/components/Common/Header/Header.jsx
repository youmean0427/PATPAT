import React from 'react';
import styles from './Header.module.scss';
import logo from 'assets/images/logo.png';
import Navbar from './Navbar';
export default function Header() {
  return (
    <header className={styles.header}>
      <div className={styles.top}>
        <div className={styles['auth-menus']}>
          <div className={styles['auth-menu-item']}>로그인</div>
        </div>
      </div>
      <div className={styles.bottom}>
        <img className={styles.logo} src={logo} alt="logo" />
        <Navbar />
      </div>
    </header>
  );
}
