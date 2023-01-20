import React from 'react';
import styles from './Navbar.module.scss';
import { NavLink } from 'react-router-dom';
import logo from 'assets/images/logo.png';
import { DarkModeOutlined } from '@mui/icons-material';
import MenuList from './MenuList';

export default function Navbar() {
  return (
    <>
      <div className={styles.left}>
        <NavLink className={styles['logo-link']} to="/">
          <div className={styles['logo-box']}>
            <img src={logo} alt="logo" />
          </div>
        </NavLink>
        <MenuList />
      </div>
      <div className={styles.right}>
        <DarkModeOutlined sx={{ fontSize: 20 }} />
        <button className={styles.login}>로그인</button>
      </div>
    </>
  );
}
