import React from 'react';
import { Outlet } from 'react-router-dom';
import styles from './ConsultingLayout.module.scss';
export default function MbtiLayout() {
  return (
    <div className={styles.layout}>
      <Outlet />
    </div>
  );
}
