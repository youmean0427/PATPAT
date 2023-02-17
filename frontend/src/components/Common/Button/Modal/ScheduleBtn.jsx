import React from 'react';
import { AiOutlineEdit } from 'react-icons/ai';
import styles from './ScheduleBtn.module.scss';
export default function ScheduleBtn({ isActive, value }) {
  if (isActive) {
    return (
      <button className={`${styles.btn} ${styles.active}`}>
        {value} <AiOutlineEdit className={styles.editIcon} />
      </button>
    );
  } else {
    return (
      <button className={styles.btn}>
        {value} <AiOutlineEdit className={styles.editIcon} />
      </button>
    );
  }
}
