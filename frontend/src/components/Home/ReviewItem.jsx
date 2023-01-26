import Card from 'components/Common/Card';
import React from 'react';
import styles from './ReviewItem.module.scss';
export default function ReviewItem({ item }) {
  const { title, content, thumbnail } = item;
  return (
    <Card>
      <img src={thumbnail} alt="thumbnail" />
      <div className={styles['desc-wrap']}>
        <div className={styles.title}>{title}</div>
        <div className={styles.content}>{content}</div>
      </div>
    </Card>
  );
}
