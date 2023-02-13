import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { getFavListListOfUser } from 'apis/api/user';
import FavoriteItem from 'components/MyPage/Items/FavoriteItem';
import styles from './MyFavorite.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import NoData from 'components/Common/NoData';

export default function MyFavorite() {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery(['getFavListListOfUser', page], () => {
    return getFavListListOfUser(LIMIT, page - 1);
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return (
    <ShelterContainer title="꾹 리스트">
      {data.totalCount === 0 ? (
        <NoData>꾹 등록 정보가 없습니다.</NoData>
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
              return <FavoriteItem key={item.spDogId} item={item} />;
            })}
          </div>
        </>
      )}
    </ShelterContainer>
  );
}
