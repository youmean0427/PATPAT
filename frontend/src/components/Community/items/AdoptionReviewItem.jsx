import Card from 'components/Common/Card';
import React, { useState } from 'react';
import styles from './AdoptionReviewItem.module.scss';
import DetailModal from 'components/Common/DetailModal';
import BoardDetail from './BoardDetail';

export default function AdoptionReviewItem({ item, change }) {
  const [modal, setModal] = useState(false);

  const openModal = () => {
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };
  return (
    <Card>
      <div
        onClick={() => {
          openModal();
        }}
      >
        <img src={item.thumbnail.filePath} alt="thumbnail" />
        <div className={styles['desc-wrap']}>
          <div className={styles.title}>{item.title}</div>
          <div className={styles.content} dangerouslySetInnerHTML={{ __html: item.content }}></div>
        </div>
      </div>
      <DetailModal open={modal} close={closeModal} title={item.title}>
        <BoardDetail boardId={item.boardId} close={closeModal} change={change} typeCode={0} />
      </DetailModal>
    </Card>
  );
}
