import React from 'react';
import ModalFrame from '../ModalFrame';
import styles from './AddProtectModal.module.scss';
export default function AddProtectModal({ isOpen, handleClickModalClose, shelterId }) {
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={700} height={600}></ModalFrame>
  );
}
