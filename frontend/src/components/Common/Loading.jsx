import React from 'react';
import styles from './Loading.module.scss';
import loading from 'assets/images/cutedog.gif';
export default function Loading() {
  return (
    <div className={styles.container}>
      <img src={loading} alt="로딩 gif" />
    </div>
  );
}
