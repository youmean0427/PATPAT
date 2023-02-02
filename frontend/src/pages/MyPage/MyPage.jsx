import React from 'react';
import styles from './MyPage.module.scss';
import ForPawMeter from 'components/MyPage/ForPawMeter';
import Contents from 'components/MyPage/Contents';
export default function MyPage() {
  return (
    <div className={styles.container}>
      <ForPawMeter />
      <hr className={styles.line} />
      <div className={styles.contents}>
        <Contents />
      </div>
    </div>
  );
}
