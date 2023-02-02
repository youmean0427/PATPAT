import React from 'react';
import { useLocation } from 'react-router-dom';

import styles from './MissingDogDetail.module.scss';
import MissingDogDetailContent from 'components/Report/MissingDog/MissingDogDetailContent';

export default function MissingDogDetail() {
  const location = useLocation();
  const item = location.state.missingId;

  return (
    <div>
      <div className={styles.title}>실종견/임보견 상세</div>
      <hr />
      <MissingDogDetailContent item={item} />
    </div>
  );
}
