import Card from 'components/Common/Card';
import React from 'react';
import styles from './ReviewItem.module.scss';
export default function ReviewItem({ item }) {
  const { boardId, author, title, content, thumbnail, count } = item;
  console.log(content);
  return (
    <Card>
      <img src={thumbnail.filePath} alt="thumbnail" />
      <div className={styles['desc-wrap']}>
        <div className={styles.title}>{title}</div>
        <div className={styles.content} dangerouslySetInnerHTML={{ __html: content }}></div>
      </div>
    </Card>
  );
}
