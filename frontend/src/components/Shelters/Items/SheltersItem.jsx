import React from 'react';
import { Link } from 'react-router-dom';
import styles from './SheltersItem.module.scss';

export default function SheltersItem({ item, sido, gugun, breed }) {
  const { shelterId, name, address, filePath, infoContent, phone } = item;

  return (
    <div
      className={
        sido === undefined && gugun === undefined && breed === undefined ? styles.shelters : styles['shelters-all']
      }
    >
      <div className={styles['shelter-img']}>
        <img src={filePath} alt="" />
      </div>
      <div className={styles['text-box']}>
        <Link to={`${shelterId}`} state={{ shelterId }}>
          <p className={styles['shelter-name']}>{name}</p>
        </Link>
        <p className={styles['shelter-address']}>{address}</p>
        <p className={styles['shelter-info']}>{infoContent}</p>
        <p className={styles['shelter-phone']}>{phone}</p>
      </div>
    </div>
  );
}
