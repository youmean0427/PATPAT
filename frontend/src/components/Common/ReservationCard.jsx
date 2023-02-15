import React from 'react';
import styles from './ReservationCard.module.scss';
export default function ReservationCard({ children }) {
  return <div className={styles.card}>{children}</div>;
}
