import React from 'react';
import styles from './Navbar.module.scss';
import { Link } from 'react-router-dom';
import logo from 'assets/images/logo.png';
import MenuList from './MenuList';

export default function Navbar() {
  return (
    <>
      <Link className={styles['logo-link']} to="/">
        <div className={styles['logo-box']}>
          <img src={logo} alt="logo" />
        </div>
      </Link>
      <MenuList />
    </>
  );
}
