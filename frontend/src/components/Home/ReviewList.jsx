import React from 'react';
import styles from './ReviewList.module.scss';
export default function ReviewList() {
  return (
    <div className={styles.container}>
      <div className={styles.title}>
        <span>가족</span>을 기다리고 있어요
      </div>
      <div className="list"></div>
    </div>
  );
}
