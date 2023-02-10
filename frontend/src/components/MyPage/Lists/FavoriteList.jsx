import { useQuery } from '@tanstack/react-query';
import { getFavListListOfUser } from 'apis/api/user';
import React, { useState } from 'react';
import styles from './FavoriteList.module.scss';
import FavoriteItem from '../Items/FavoriteItem';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
export default function FavoriteList() {
  const userId = JSON.parse(localStorage.getItem('user')).userId;
  const [page, setPage] = useState(1);
  const LIMIT = 8;
  const { data, isLoading } = useQuery({
    queryKey: ['myFavoriteList'],
    queryFn: () => getFavListListOfUser(LIMIT, page - 1),
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
        {data.totalCount === 0 ? (
          <div className={styles['no-data']}>등록된 '꾹' 정보가 없습니다.</div>
        ) : (
          data.list.map(item => <FavoriteItem key={item.spDogId} item={item} />)
        )}
      </div>
    </ShelterContainer>
  );
}
