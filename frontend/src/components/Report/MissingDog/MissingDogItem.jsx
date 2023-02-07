import Card from 'components/Common/Card';
import React from 'react';
import styles from './MissingDogItem.module.scss';
import { Link } from 'react-router-dom';
export default function MissingDogItem({ item }) {
  const { missingId, title, fileUrlList, breedName, content } = item;
  console.log(item);
  return (
    <div>
      <Link to={`missing/${missingId}`} state={{ missingId }}>
        <Card>
          {/* <img src={fileUrlList[0]} alt={fileUrlList[0]} /> */}
          <div className={styles.description}>
            <div className={styles.title}>{title}</div>
            <div className={styles.breedName}>{breedName}</div>
            <div className={styles.content}>{content}</div>
          </div>
        </Card>
      </Link>
      <br />
    </div>
  );
}
