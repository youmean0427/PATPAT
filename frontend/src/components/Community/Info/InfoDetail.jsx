import React from 'react';
import { useNavigate, useLocation, Link } from 'react-router-dom';
import styles from '../Detail.module.scss';
import { DeleteBoard } from 'apis/api/board';
import CommentList from '../Comment/CommentLIst';
import CommentWrite from '../Comment/CommentWrite';
import { Carousel } from 'react-carousel-minimal';
import { useQuery } from '@tanstack/react-query';
import { getBoardDetail } from 'apis/api/board';

export default function InfoDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state.item;
  const { isLoading, data: detailData } = useQuery(['boardDetail'], () => getBoardDetail(data.boardId));
  if (isLoading) {
    return <div>Loading...</div>;
  }
  const fileList = detailData.fileUrlList;
  const images = [];
  fileList.forEach((item, index) => {
    images.push({ image: item['filePath'] });
  });
  const onClickBtn = () => {
    navigate('/community/info');
  };
  const slideNumberStyle = {
    fontSize: '20px',
    fontWeight: 'bold',
  };

  return (
    <div className={styles['detail-container']}>
      <div className={styles['detail-title']}>
        <label>{data.title}</label>
      </div>
      <div className={styles['detail-main']}>
        <div>
          <div className={styles['sub-title']}>
            <div className={styles['detail-view-row']}>
              <div>{data.author}</div>
            </div>
            <div className={styles['detail-view-row']}>
              <label>조회수 : {data.count}</label>
            </div>
            <div className={styles['detail-view-row']}>
              <label>{data.registDate}</label>
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
            {data.content === null
              ? null
              : data.content
                  .replace(/(<([^>]+)>)/gi, ' ')
                  .replace(/"/g, ' ')
                  .replace(/"n/, ' ')
                  .replace(/&/g, ' ')
                  .replace(/ /g, ' ')}
          </div>
        </div>

        <div className={styles['detail-comment']}>
          <div className={styles['detail-comment-list']}>
            <CommentList boardId={data.boardId} />
          </div>
          <div className={styles['detail-comment-write']}>
            <CommentWrite boardId={data.boardId} />
          </div>
        </div>
      </div>
      <div className={styles['detail-btns']}>
        <div className={styles['detail-btn']}>
          <Link to={`/community/infoupdate/${data.boardId}`} state={{ item: data }}>
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
              DeleteBoard(data.boardId);
              onClickBtn();
            }}
          >
            삭제
          </button>
        </div>
      </div>
    </div>
  );
}
