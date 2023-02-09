import React, { useState } from 'react';
import styles from './Boards.module.scss';
import ReviewList from './Lists/ReviewList';
import ShareList from './Lists/ShareList';
import InfoList from './Lists/InfoList';

export default function Boards() {
  const [category, setCategory] = useState([true, false, false]);
  const [typeCode, setTypeCode] = useState(0);

  return (
    <div className={styles['board-list']}>
      <div className={styles.category}>
        <ul>
          <li
            className={category[0] ? styles.click : styles.default}
            onClick={() => {
              setCategory([true, false, false]);
              setTypeCode(0);
            }}
          >
            입양후기
          </li>
          <li
            className={category[1] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, true, false]);
              setTypeCode(1);
            }}
          >
            무료나눔
          </li>
          <li
            className={category[2] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, false, true]);
              setTypeCode(2);
            }}
          >
            정보공유
          </li>
        </ul>
      </div>
      <br />
      <div className={styles.list}>
        {typeCode === 1 ? (
          <ReviewList code={typeCode} />
        ) : typeCode === 2 ? (
          <ShareList code={typeCode} />
        ) : (
          <InfoList code={typeCode} />
        )}
      </div>
    </div>
  );
}
