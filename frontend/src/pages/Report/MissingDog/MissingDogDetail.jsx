import React from 'react';
import { useLocation } from 'react-router-dom';
import { Link } from 'react-router-dom';
import styles from './MissingDogDetail.module.scss';
import MissingDogDetailContent from 'components/Report/MissingDog/MissingDogDetailContent';

export default function MissingDogDetail() {
  const location = useLocation();
  const item = location.state.missingId;

  return (
    <div>
      <MissingDogDetailContent item={item} />
      <hr />
      <div className={styles['container-button']}>
        <button className={styles.button}>이전</button>

        <Link to="/report">
          <button className={styles.button}>목록</button>
        </Link>

        <button className={styles.button}>다음</button>
      </div>
    </div>
  );
}
