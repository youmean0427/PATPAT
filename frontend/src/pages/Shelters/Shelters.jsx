import React from 'react';
import styles from './Shelters.module.scss';
import Contents from 'components/Shelters/Contents';

export default function MyPage() {
  return (
    <div className={styles.container}>
      <Contents />
    </div>
  );
}
