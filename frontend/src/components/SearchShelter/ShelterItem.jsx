import React from 'react';
import styles from './ShelterItem.module.scss';
import shelter from 'assets/images/default-shelter.png';
import { useNavigate } from 'react-router-dom';
export default function ShelterItem({ item }) {
  const navigate = useNavigate();
  return (
    <div
      onClick={() => {
        navigate(`/shelter/${item.shelterId}/intro`);
      }}
      className={styles.container}
    >
      <div className={styles.thumbnail}>
        <img src={shelter} alt="shelter" />
      </div>
      <div className={styles.info}>{item.name}</div>
    </div>
  );
}
