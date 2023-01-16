import React from 'react';
import styles from './Header.module.css';
export default function Header() {
  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <img src="../../assets/images/logo.png" alt="logo" />
      </div>
      <div className={styles.menu__wrap}>
        <div className={styles.menu}>소개</div>
        <div className={styles.menu}>보호소</div>
        <div className={styles.menu}>신고</div>
        <div className={styles.menu}>봉사</div>
        <div className={styles.menu}>커뮤니티</div>
      </div>
    </header>
  );
}
