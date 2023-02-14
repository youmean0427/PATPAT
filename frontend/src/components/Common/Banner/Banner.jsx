import React from 'react';
import styles from './Banner.module.scss';
import pat from 'assets/images/pat.png';
export default function Banner({ title }) {
  return (
    <div>
      <div className={styles.banner}>
        <div className={styles.container}>
          <div className={styles.item_1}>
            <div></div>
          </div>
          <div className={styles.item_1}>
            <div className={styles.title}>{title}</div>
          </div>
          <div className={styles.item_2}>
            <div className={styles['pat-container']}>
              <img className={styles.pat} src={pat} alt="pat" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
