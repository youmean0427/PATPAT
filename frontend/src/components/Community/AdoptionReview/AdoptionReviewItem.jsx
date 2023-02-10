import Card from 'components/Common/Card';
import React, { useState } from 'react';
import styles from './AdoptionReviewItem.module.scss';
import Modal from 'components/Common/Modal';
import { Link } from 'react-router-dom';
import CommentList from '../Comment/CommentLIst';
import CommentWrite from '../Comment/CommentWrite';
import { OtherHouses } from '@mui/icons-material';

export default function AdoptionReviewItem({ item }) {
  const [modal, setModal] = useState(false);

  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  const images = [
    'https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_262/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg',
    'http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg',
    'https://mblogthumb-phinf.pstatic.net/20130303_92/ovcharka_no1_1362279507519u1D77_JPEG/2.jpg?type=w2',
    'https://image-notepet.akamaized.net/resize/620x-/seimage/20171124%2F7d893bf5da1a85fd9e34683179814863.jpg',
  ];

  return (
    <div>
      <Card>
        <img src={images[0]} alt="thumbnail" onClick={openModal} />
        <div className={styles['desc-wrap']}>
          <div className={styles.title}>{item.title}</div>
          <div className={styles.content}>{item.content}</div>
        </div>
      </Card>
      <Modal open={modal} onClose={closeModal} close={closeModal}>
        <div className={styles['detail-view-wrapper']}>
          {item ? (
            <>
              <div className={styles['detail-title']}>
                <label>{item.title}</label>
              </div>
              <div className={styles['sub-title']}>
                <div className={styles['detail-view-row']}>
                  <div>{item.author}</div>
                </div>
                <div className={styles['detail-view-row']}>
                  <label>{item.count}</label>
                </div>
                <div className={styles['detail-view-row']}>
                  <label>{item.registDate}</label>
                </div>
                <div className={styles['detail-btn']}>
                  <button>삭제</button>
                </div>
                <div className={styles['detail-btn']}>
                  <Link to={`/community/adoptionreviewupdate/${item.boardId}`} state={{ item: item }}>
                    <button>수정</button>
                  </Link>
                </div>
              </div>
              <div className={styles['detail-img']}>
                <img src={images[0]} alt="thumbnail" />
              </div>
              <div className={styles['detail-view-content']}>
                {item.content === null
                  ? null
                  : item.content
                      .replace(/(<([^>]+)>)/gi, ' ')
                      .replace(/"/g, ' ')
                      .replace(/\"n/, ' ')
                      .replace(/&/g, ' ')
                      .replace(/ /g, ' ')}
              </div>
              <div>
                <CommentList boardId={item.boardId} />
              </div>
              <div>
                <CommentWrite boardId={item.boardId} />
              </div>
            </>
          ) : (
            '해당 게시글을 찾을 수 없습니다.'
          )}
        </div>
      </Modal>
    </div>
  );
}
