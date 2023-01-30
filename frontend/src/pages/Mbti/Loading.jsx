import React from 'react';
import loading from 'assets/images/loading.gif';
import styles from './Loading.module.scss';
export default function Loading() {
  return (
    <div className={styles.loading}>
      <img src={loading} alt="loading" />
      <span>PetBTI 분석중 ...</span>
    </div>
  );
}
