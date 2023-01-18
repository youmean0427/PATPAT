import React from 'react';
import styles from './Header.module.scss';
import logo from 'assets/images/logo.png';
import Navbar from './Navbar';
export default function Header() {
  return (
    <header className={styles.header}>
      <div className={styles.left}>
        <img className={styles.logo} src={logo} alt="logo" />
      </div>
      <div className={styles.right}>
        <div className={styles['auth-menus']}>
          <div className={styles['auth-menu']}>로그인</div>
        </div>
        <Navbar />
      </div>
    </header>
  );
}
