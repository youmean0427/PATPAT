import React from 'react';
import styles from './ShelterContainer.module.scss';
export default function ShelterContainer({ title, children }) {
  return (
    <div className={styles.container}>
      <div className={styles.title}>{title}</div>
      {children}
    </div>
  );
}
