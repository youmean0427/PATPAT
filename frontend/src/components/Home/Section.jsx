import React from 'react';
import styles from './Section.module.scss';
export default function Section({ accent, basic = '', children }) {
  return (
    <div className={styles['container']}>
      <div className={styles.title}>
        <span>{accent}</span>
        {basic}
      </div>
      {children}
    </div>
  );
}
