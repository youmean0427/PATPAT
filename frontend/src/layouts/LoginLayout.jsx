import React from 'react';
import { Outlet } from 'react-router-dom';
import styles from './LoginLayout.module.scss';
export default function LoginLayout() {
  return (
    <div className={styles.layout}>
      <Outlet />
    </div>
  );
}
