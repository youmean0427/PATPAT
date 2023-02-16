import { useQuery } from '@tanstack/react-query';
import { getBoardDetail, DeleteBoard } from 'apis/api/board';
import React from 'react';
import styles from './BoardDetail.module.scss';
import Carousel from 'react-carousel-minimal/dist/components/Carousel';
import './Carousel.css';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';
import Comment from './Comment';

export default function BoardDetail({ boardId, close, change, typeCode }) {
  const navigate = useNavigate();
  const userId = JSON.parse(localStorage.getItem('user')).userId;

  const { data, isLoading } = useQuery({
    queryKey: ['getBoardDetail', boardId, change],
    queryFn: () => {
      return getBoardDetail(boardId);
    },
  });

  if (isLoading) return;

  const fileList = data.fileUrlList;
  const images = [];
  fileList.forEach((img, index) => {
    images.push({ image: img.filePath });
  });

  const slideNumberStyle = {
    fontSize: '10px',
    fontWeight: 'bold',
  };

  const handleDeleteBoard = () => {
    DeleteBoard(data.boardId);
    Swal.fire({
      title: '삭제되었습니다',
      icon: 'success',
    }).then(() => {
      close();
      change(cur => !cur);
    });
  };
  return (
    <div>
      <div className={styles.title}>{data.title}</div>
      <div className={styles['board-desc']}>
        <div className={styles.author}>{data.author}</div>
        <div className={styles.date}>
          {data.registDate[0]}-{data.registDate[1] <= 9 ? '0' + data.registDate[1] : data.registDate[1]}-
          {data.registDate[2] <= 9 ? '0' + data.registDate[2] : data.registDate[2]}
        </div>
        <div className={styles.count}>조회수 : {data.count}</div>
      </div>
      <hr className={styles.line} />
      <div styles={styles['slide-img']}>
        <Carousel
          width="300px"
          height="300px"
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
        ></Carousel>
      </div>
      <div className={styles.content} dangerouslySetInnerHTML={{ __html: data.content }}></div>
      {/* <Comment boardId={data.boardId} /> */}

      {data.userId === userId ? (
        <div className={styles.buttons}>
          <button onClick={() => navigate('/community/update', { state: { stateCode: typeCode, data: data } })}>
            수정
          </button>
          <button onClick={() => handleDeleteBoard()}>삭제</button>
        </div>
      ) : null}
    </div>
  );
}
