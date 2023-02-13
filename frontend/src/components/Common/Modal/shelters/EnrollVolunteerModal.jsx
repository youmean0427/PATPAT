import React from 'react';
import ModalFrame from '../ModalFrame';
import styles from './EnrollVolunteerModal.module.scss';
export default function EnrollVolunteerModal({ isOpen, handleClickModalClose }) {
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={400} height={200}>
      <div className={styles.container}>
        <div className={styles.title}>봉사 공고 등록</div>
        <form></form>
      </div>
    </ModalFrame>
  );
}
