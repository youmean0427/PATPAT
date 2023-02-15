import React from 'react';
import styles from './ConsultingCard.module.scss';
export default function VolunteerCard({ children }) {
  return <div className={styles.card}>{children}</div>;
}
