import React from 'react';
import styles from './ConsultingBadge.module.scss';
export default function ConsultingBadge({ handleClickModalOpen, state, stateCode }) {
  const customStyle = () => {
    if (stateCode === 0) {
      return `${styles.badge} ${styles.zero}`;
    } else if (stateCode === 1) {
      return `${styles.badge} ${styles.one}`;
    } else if (stateCode === 2) {
      return `${styles.badge} ${styles.two}`;
    } else if (stateCode === 3) {
      return `${styles.badge} ${styles.three}`;
    } else if (stateCode === 4) {
      return `${styles.badge} ${styles.four}`;
    } else if (stateCode === 5) {
      return `${styles.badge} ${styles.five}`;
    } else {
      return `${styles.badge} ${styles.six}`;
    }
  };

  return (
    <div onClick={handleClickModalOpen} className={customStyle()}>
      {state}
    </div>
  );
}
