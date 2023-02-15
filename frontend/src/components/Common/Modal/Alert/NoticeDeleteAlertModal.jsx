import { useMutation, useQueryClient } from '@tanstack/react-query';
import { deleteVolNotice } from 'apis/api/volunteer';
import React from 'react';
import ModalFrame from '../ModalFrame';
import styles from './NoticeDeleteAlertModal.module.scss';
export default function NoticeDeleteAlertModal({
  setClick,
  isOpen,
  handleClickModalClose,
  handleClickModalClose2,
  noticeId,
}) {
  const { mutate } = useMutation(['deleteVolNotice'], () => deleteVolNotice(noticeId), {
    onSuccess: res => {
      console.log(res);
      handleClickModalClose();
      handleClickModalClose2();
      setClick(prev => !prev);
    },
  });

  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={400} height={200}>
      <div className={styles.title}>공고 삭제 하시겠습니까 ? </div>
      <div className={styles['btn-wrap']}>
        <button onClick={mutate}>확인</button>
        <button onClick={handleClickModalClose}>취소</button>
      </div>
    </ModalFrame>
  );
}
