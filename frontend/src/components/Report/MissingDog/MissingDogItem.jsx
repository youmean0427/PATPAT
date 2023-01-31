import Card from 'components/Common/Card';
import React from 'react';
import styles from './MissingDogItem.module.scss';
import { Link } from 'react-router-dom';
export default function MissingDogItem({ item }) {
  const { missingId, title, name, fileUrlList, breedName, content } = item;
  // console.log(item);
  return (
    <div>
      <Link to={`missing/${missingId}`} state={{ item }}>
        <Card>
          <img src={fileUrlList} alt={name} />
          <div className={styles.description}>
            <div className={styles.name}>{title}</div>
            <div className={styles.kind}>{breedName}</div>
            <div className={styles.content}>{content}</div>
          </div>
        </Card>
      </Link>
      <br />
    </div>
  );
}
