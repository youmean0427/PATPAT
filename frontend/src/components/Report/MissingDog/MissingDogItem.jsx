import Card from 'components/Common/Card';
import React from 'react';
import styles from './MissingDogItem.module.scss';
export default function MissingDogItem({ item }) {
  const { title, name, fileUrlList, breedName, content } = item;

  return (
    <div>
      <Card>
        <img src={fileUrlList} alt={name} />
        <div className={styles.description}>
          <div className={styles.name}>{title}</div>
          <div className={styles.kind}>{breedName}</div>
          <div className={styles.content}>{content}</div>
        </div>
      </Card>
      <br />
    </div>
  );
}
