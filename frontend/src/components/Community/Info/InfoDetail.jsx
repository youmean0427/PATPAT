import React from 'react';
import { useNavigate, useLocation, Link } from 'react-router-dom';
import styles from '../Detail.module.scss';
import { DeleteBoard } from 'apis/api/board';
import CommentList from '../Comment/CommentLIst';
import CommentWrite from '../Comment/CommentWrite';
import { Carousel } from 'react-carousel-minimal';

export default function InfoDetail() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state.item;
  const images = [
    {
      image: 'https://image-notepet.akamaized.net/resize/620x-/seimage/20171124%2F7d893bf5da1a85fd9e34683179814863.jpg',
    },
    {
      image:
        'https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_262/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg',
    },
    {
      image: 'https://image-notepet.akamaized.net/resize/620x-/seimage/20171124%2F7d893bf5da1a85fd9e34683179814863.jpg',
    },
    { image: 'http://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg' },
    {
      image: 'https://image-notepet.akamaized.net/resize/620x-/seimage/20171124%2F7d893bf5da1a85fd9e34683179814863.jpg',
    },
    { image: 'https://mblogthumb-phinf.pstatic.net/20130303_92/ovcharka_no1_1362279507519u1D77_JPEG/2.jpg?type=w2' },
  ];
  const onClickBtn = () => {
    navigate('/community/info');
  };
  const slideNumberStyle = {
    fontSize: '20px',
    fontWeight: 'bold',
  };

  return (
    <div className={styles['detail-container']}>
      {data ? (
        <>
          <div className={styles['detail-title']}>
            <label>{data.title}</label>
          </div>
          <div>
            <div className={styles['sub-title']}>
              <div className={styles['detail-view-row']}>
                <div>{data.author}</div>
              </div>
              <div className={styles['detail-view-row']}>
                <label>{data.count}</label>
              </div>
              <div className={styles['detail-view-row']}>
                <label>{data.registDate}</label>
              </div>
            </div>
            <div className={styles['detail-btns']}>
              <div className={styles['detail-btn']}>
                <Link to={`/community/infoupdate/${data.boardId}`} state={{ item: data }}>
                  <button>수정</button>
                </Link>
              </div>
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
          <div className={styles['detail-view-content']}>
            {data.content === null
              ? null
              : data.content
                  .replace(/(<([^>]+)>)/gi, ' ')
                  .replace(/"/g, ' ')
                  .replace(/\"n/, ' ')
                  .replace(/&/g, ' ')
                  .replace(/ /g, ' ')}
          </div>
          <div>
            <CommentList boardId={data.boardId} />
          </div>
          <div>
            <CommentWrite boardId={data.boardId} />
          </div>
        </>
      ) : (
        '해당 게시글을 찾을 수 없습니다..'
      )}
      <button onClick={onClickBtn}>목록으로 돌아가기</button>
    </div>
  );
}
