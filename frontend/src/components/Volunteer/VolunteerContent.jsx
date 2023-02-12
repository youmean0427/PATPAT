import styles from './VolunteerContent.module.scss';
import React from 'react';
import image from '../../../src/assets/images/volunteer.png';
import { Link } from 'react-router-dom';

export default function VolunteerContent() {
  return (
    <div>
      <br />
      <div className={styles.container}>
        <img className={styles.img} src={image} alt="volunteer" />
        <br />
        <Link to="/volunteer/search">
          <figure className={styles.circle}>
            <div className={styles['mask-b']}>
              <span className={styles['ok-btn']}>봉사 신청하기</span>
              <div className={styles.cursor}></div>
            </div>
          </figure>
        </Link>
        <br />
      </div>
    </div>
  );
}
