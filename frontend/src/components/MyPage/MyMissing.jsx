import { useQuery } from '@tanstack/react-query';
import { getSimilarDogList } from 'apis/api/report';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import styles from './MyMissing.module.scss';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import NoData from 'components/Common/NoData';
import SearchShelterItem from './Items/SearchShelterItem';

export default function MyMissing() {
  const [page, setPage] = useState(1);
  const LIMIT = 8;
  const location = useLocation();
  const userName = JSON.parse(localStorage.getItem('user')).userName;
  const missingId = location.state.missingId;
  const dogName = location.state.name;
  const thumbnail = location.state.thumbnail.filePath;

  const { data, isLoading } = useQuery({
    queryKey: ['getSimilarDogList', page],
    queryFn: () => {
      return getSimilarDogList(missingId, LIMIT, page - 1);
    },
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };

  if (isLoading) return;

  return (
    <div className={styles.container}>
      <div className={styles['missing-detail']}>
        <div className={styles['dog-img']}>
          <img src={thumbnail} alt="" />
        </div>
        <div className={styles.content}>
          <p>
            <span>{userName}</span>님이 찾고 계신 <span>{dogName}</span>이(가) 있을만한 보호소입니다.
          </p>
          <p>
            반경 10km 내의 보호소 중 총 <span>{data.totalCount}</span>개가 검색되었습니다.
          </p>
          <br />
          <p>해당 보호소 페이지로 이동하여 화상 상담을 신청할 수 있습니다.</p>
          <p>
            반드시 <span>{dogName}</span>을(를) 찾기를 <span>PATPAT</span>이 기원합니다.
          </p>
        </div>
      </div>
      <hr className={styles.line} />
      <div className={styles['shelter-list']}>
        {data.totalCount === 0 ? (
          <NoData>검색된 보호소가 없습니다.</NoData>
        ) : (
          <>
            <div className={styles.pagination}>
              <button
                onClick={handleClickPrev}
                className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
                disabled={page === 1 ? true : false}
              >
                <MdArrowBackIosNew />
              </button>
              <span>{page}</span>
              <button
                onClick={handleClickNext}
                className={
                  data.totalPage === 0 || page === data.totalPage
                    ? `${styles.button} ${styles.disabled}`
                    : styles.button
                }
                disabled={data.totalPage === 0 || page === data.totalPage ? true : false}
              >
                <MdArrowForwardIos />
              </button>
            </div>
            <div className={styles.list}>
              {data.list.map(item => {
                return <SearchShelterItem key={item.shelterId} item={item} />;
              })}
            </div>
          </>
        )}
      </div>
    </div>
  );
}
