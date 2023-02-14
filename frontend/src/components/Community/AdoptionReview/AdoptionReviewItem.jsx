import Card from 'components/Common/Card';
import React, { useState } from 'react';
import styles from './AdoptionReviewItem.module.scss';
import Modal from 'components/Common/Modal';
import { Link, useNavigate } from 'react-router-dom';
import CommentList from '../Comment/CommentLIst';
import CommentWrite from '../Comment/CommentWrite';
import { DeleteBoard, getBoardDetail } from 'apis/api/board';
import { Carousel } from 'react-carousel-minimal';
import { useQuery } from '@tanstack/react-query';

export default function AdoptionReviewItem({ item }) {
  const [modal, setModal] = useState(false);
  const navigate = useNavigate();
  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };
  const { isLoading, data: detailData } = useQuery(['boardDetail'], () => getBoardDetail(item.boardId));
  if (isLoading) {
    return <div>Loading...</div>;
  }

  const fileList = detailData.fileUrlList;
  const images = [];
  fileList.forEach((item, index) => {
    images.push({ image: item['filePath'] });
  });
  const onClickBtn = () => {
    navigate('/community/adoptionreview');
  };
  const slideNumberStyle = {
    fontSize: '20px',
    fontWeight: 'bold',
  };
  return (
    <div>
      <Card className={styles['item-card']}>
        <img src={images[0]['image']} alt="thumbnail" onClick={openModal} />
        <div className={styles['desc-wrap']}>
          <div className={styles['card-title']}>{item.title}</div>
          <div className={styles.content}>
            {item.content === null
              ? null
              : item.content
                  .replace(/(<([^>]+)>)/gi, ' ')
                  .replace(/&quot;/g, ' ')
                  .replace(/"n/, ' ')
                  .replace(/&amp;/g, ' ')
                  .replace(/&nbsp;/g, ' ')}
          </div>
        </div>
      </Card>
      <Modal open={modal} onClose={closeModal} close={closeModal}>
        <div className={styles['detail-view-wrapper']}>
          <div className={styles['detail-title']}>
            <label>{item.title}</label>
          </div>
          <div className={styles['detail-main']}>
            <div>
              <div className={styles['sub-title']}>
                <div className={styles['detail-view-row']}>
                  <div>{item.author}</div>
                </div>
                <div className={styles['detail-view-row']}>
                  <label>조회수 : {item.count}</label>
                </div>
                <div className={styles['detail-view-row']}>
                  <label>{item.registDate}</label>
                </div>
              </div>
            </div>

            <div className={styles['detail-img-box']}>
              <Carousel
                data={images}
                radius="10px"
                slideNumber={true}
                slideNumberStyle={slideNumberStyle}
                dots={true}
                pauseIconColor="white"
                pauseIconSize="40px"
                slideBackgroundColor="darkgrey"
                slideImageFit="cover"
                thumbnails={true}
                thumbnailWidth="100px"
              ></Carousel>
            </div>
            <div className={styles['detail-content']}>
              <div className={styles['detail-view-content']}>
                {item.content === null
                  ? null
                  : item.content
                      .replace(/(<([^>]+)>)/gi, ' ')
                      .replace(/"/g, ' ')
                      .replace(/"n/, ' ')
                      .replace(/&/g, ' ')
                      .replace(/ /g, ' ')}
              </div>
            </div>

            <div className={styles['detail-comment']}>
              <div className={styles['detail-comment-list']}>
                <CommentList boardId={item.boardId} />
              </div>
              <div className={styles['detail-comment-write']}>
                <CommentWrite boardId={item.boardId} />
              </div>
            </div>
          </div>
          <div className={styles['detail-btns']}>
            <div className={styles['detail-btn']}>
              <Link to={`/community/adoptionreviewupdate/${item.boardId}`} state={{ item: item }}>
                <button className={styles['soojung']}>수정</button>
              </Link>
            </div>
            <button
              className={styles['detail-btn']}
              onClick={() => {
                onClickBtn();
              }}
            >
              목록으로 돌아가기
            </button>
            <div className={styles['detail-btn']}>
              <button
                onClick={() => {
                  DeleteBoard(item.boardId);
                  onClickBtn();
                }}
              >
                삭제
              </button>
            </div>
          </div>
        </div>
      </Modal>
    </div>
  );
}
