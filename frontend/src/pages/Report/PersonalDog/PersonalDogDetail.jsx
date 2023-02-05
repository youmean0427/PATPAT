import React from 'react';
import { useLocation } from 'react-router-dom';
import { Link } from 'react-router-dom';
import styles from './PersonalDogDetail.module.scss';
import Button from '@mui/material/Button';
import DogDetailContent from 'components/Report/DogDetailContent';
export default function PersonalDogDetail() {
  const location = useLocation();
  const item = location.state.personalProtectionId;
  const state = 1;
  return (
    <div>
      <DogDetailContent item={item} state={state} />
      <hr />
      <div className={styles['container-button']}>
        <Button variant="contained" className={styles.button}>
          이전
        </Button>

        <Link to="/report">
          <Button variant="contained" className={styles.button}>
            목록
          </Button>
        </Link>

        <Button variant="contained" className={styles.button}>
          다음
        </Button>
      </div>
    </div>
  );
}
