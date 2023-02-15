import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { getMissingDogListOfUser } from 'apis/api/report';
import MissingDogItem from 'components/MyPage/Items/MissingDogItem';
import styles from './MyFamily.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import NoData from 'components/Common/NoData';

export default function MyFamily() {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery(['getMissingDogListOfUser', page], () => {
    return getMissingDogListOfUser(LIMIT, page - 1);
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;

  return (
    <ShelterContainer title="내 가족 찾기">
      {data.totalCount === 0 ? (
        <NoData>등록된 실종 공고가 없습니다.</NoData>
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
                data.totalPage === 0 || page === data.totalPage ? `${styles.button} ${styles.disabled}` : styles.button
              }
              disabled={data.totalPage === 0 || page === data.totalPage ? true : false}
            >
              <MdArrowForwardIos />
            </button>
          </div>
          <div className={styles.list}>
            {data.list.map(item => {
              return <MissingDogItem key={item.missingId} item={item} />;
            })}
          </div>
        </>
      )}
    </ShelterContainer>
  );
}
