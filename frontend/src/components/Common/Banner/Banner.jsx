import React from 'react';
import styles from './Banner.module.scss';
import pat from 'assets/images/pat.png';
export default function Banner({ title }) {
  return (
    <div>
      <div className={styles.banner}>
        <div className={styles.container}>
          <div className={styles.title}>{title}</div>
          <div className={styles.img}>
            <img src={pat} alt="" />
          </div>
        </div>
      </div>
    </div>
  );
}
