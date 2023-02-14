import React from 'react';
import { Outlet } from 'react-router-dom';
import styles from './PlainLayout.module.scss';
export default function PlainLayout() {
  return (
    <div className={styles.layout}>
      <Outlet />
    </div>
  );
}
