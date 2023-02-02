import React from 'react';
import styles from './SheltersItem.module.scss';

export default function SheltersItem({ item }) {
  const isLogin = localStorage.getItem('isLogin');
  const { name, address, filePath, infoContent, phone } = item;

  return (
    <div className={isLogin ? styles['shelters-login'] : styles.shelters}>
      <div className={styles['shelter-img']}>
        <img src={filePath} alt="" />
      </div>
      <div className={styles['text-box']}>
        <p className={styles['shelter-name']}>{name}</p>
        <p className={styles['shelter-address']}>{address}</p>
        <p className={styles['shelter-info']}>{infoContent}</p>
        <p className={styles['shelter-phone']}>{phone}</p>
      </div>
    </div>
  );
}
