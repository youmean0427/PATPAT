import React from 'react';
import styles from './MbtiContainer.module.scss';
export default function MbtiContainer({ children }) {
  return (
    <div className={styles.wrapper}>
      <div className={styles.container}>{children}</div>
    </div>
  );
}
