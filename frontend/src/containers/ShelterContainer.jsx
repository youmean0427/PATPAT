import ModalPortal from 'components/Common/Modal/ModalPortal';
import EditInfoModal from 'components/Common/Modal/shelters/EditInfoModal';
import useModal from 'hooks/useModal';
import React from 'react';
import styles from './ShelterContainer.module.scss';
export default function ShelterContainer({ title, children }) {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  return (
    <div className={styles.container}>
      <div className={styles.title}>{title}</div>
      <button className={styles.button} onClick={handleClickModalOpen}>
        수정하기
      </button>
      {children}
      {isOpen && <EditInfoModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} />}
    </div>
  );
}
