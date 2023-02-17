import Card from 'components/Common/Card';
import ReviewModal from 'components/Common/Modal/reviews/ReviewModal';
import useModal from 'hooks/useModal';
import React from 'react';
import styles from './ReviewItem.module.scss';
export default function ReviewItem({ item }) {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  const { boardId, title, content, thumbnail } = item;
  return (
    <>
      <Card>
        <div className={styles.wrapper} onClick={handleClickModalOpen}>
          <img src={thumbnail.filePath} alt="thumbnail" />
          <div className={styles['desc-wrap']}>
            <div className={styles.title}>{title}</div>
            <div className={styles.content} dangerouslySetInnerHTML={{ __html: content }}></div>
          </div>
        </div>
      </Card>
      {isOpen && <ReviewModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} boardId={boardId} />}
    </>
  );
}
