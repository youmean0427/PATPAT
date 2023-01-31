import React from 'react';
import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';

export default function MissingDogItem({ item }) {
  const { fileUrlList, name } = item;

  return (
    <div className={styles.card}>
      <Card>
        <div>
          <img src={fileUrlList} alt="" />
        </div>
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
        </div>
      </Card>
    </div>
  );
}
