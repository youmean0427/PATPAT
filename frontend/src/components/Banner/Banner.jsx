import React from 'react';
import styles from './Banner.module.scss';
import pat from '../../assets/images/pat.png';
export default function Banner(props) {
  return (
    <div>
      <div className={styles.banner}>
        <div className={styles.container}>
          <div className={styles.item_1}>
            <div></div>
          </div>
          <div className={styles.item_1}>
            <div className={styles.title}>{props.title}</div>
          </div>
          <div className={styles.item_2}>
            <div className={styles.pat}>
              <img src={pat} alt="pat" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
