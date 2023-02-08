import React from 'react';
import styles from './OpenModalBtn.module.scss';
import { AiOutlineEdit } from 'react-icons/ai';
export default function OpenModalBtn({ handleClickModalOpen }) {
  return <AiOutlineEdit className={styles.button} onClick={handleClickModalOpen} />;
}
