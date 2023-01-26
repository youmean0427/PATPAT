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
        <Link to="/volunteer/address">
          <button className={styles['volunteer-button']}>봉사신청</button>
        </Link>
        <br />
      </div>
    </div>
  );
}
