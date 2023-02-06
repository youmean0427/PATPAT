import React from 'react';
import styles from './Navbar.module.scss';
export default function Navbar({ children }) {
  return (
    <div className={styles.navbar}>
      <div className={styles.navbar__menu}>{children}</div>
    </div>
  );
}
