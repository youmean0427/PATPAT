import Card from 'components/Common/Card';
import React from 'react';
import styles from './PersonalDogItem.module.scss';
export default function PersonalDogItem({ item }) {
  const { title, name, fileUrlList, breedName, content } = item;
  return (
    <Card>
      <img src={fileUrlList} alt={name} />

      <div className={styles.description}>
        <div className={styles.name}>{title}</div>
        <div className={styles.kind}>{breedName}</div>
        <div className={styles.content}>{content}</div>
      </div>
    </Card>
  );
}
