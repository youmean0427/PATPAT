import Card from 'components/Common/Card';
import React from 'react';
import styles from './MissingDogItem.module.scss';
export default function MissingDogItem({ item }) {
  const { title, name, weight, fileUrlList } = item;
  return (
    <Card>
      <div className={styles.missingLogo}>실종</div>
      <img src={fileUrlList} alt={name} />

      <div className={styles.description}>
        <div className={styles.name}>{title}</div>
        <div className={styles.kind}>{name}</div>
        <div className={styles.age}>{weight}</div>
      </div>
    </Card>
  );
}
