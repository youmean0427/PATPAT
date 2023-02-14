import { useQuery } from '@tanstack/react-query';
import { getMissingDogList } from 'apis/api/report';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
import React, { useEffect, useState } from 'react';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { MdArrowBackIosNew, MdArrowForwardIos } from 'react-icons/md';
import { useRecoilState } from 'recoil';
import { isLoginState } from 'recoil/atoms/user';

export default function MissingDogList({ genderCode, breedCode }) {
  const [isLogin] = useRecoilState(isLoginState);

  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList', breedCode.value, genderCode.value, page],
    queryFn: () => getMissingDogList(breedCode.value, genderCode.value, LIMIT, page - 1),
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };

  // ;
  if (isLoading) return;
  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}>
          <div className={styles.pagination}>
            <button
              onClick={handleClickPrev}
              className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
              disabled={page === 1 ? true : false}
            >
              <MdArrowBackIosNew />
            </button>
            <button
              onClick={handleClickNext}
              className={
                page === data.totalPage || data.totalPage === 0 ? `${styles.button} ${styles.disabled}` : styles.button
              }
              disabled={page === data.totalPage || data.totalPage === 0 ? true : false}
            >
              <MdArrowForwardIos />
            </button>
          </div>
        </div>
      </div>
      <div className={styles.container}>
        {data.totalCount !== 0 ? (
          <div className={styles.list}>
            {data.list.map((item, index) => (
              <MissingDogItem key={index} item={item} />
            ))}
          </div>
        ) : (
          <div className={styles.non}>
            <span>검색결과가 없습니다.</span>
          </div>
        )}
        <div className={styles.navBar}>
          {isLogin === true ? (
            <Navbar className={styles.navBar}>
              <MenuLink move="create" value="글쓰기" />
            </Navbar>
          ) : null}
        </div>
      </div>
    </div>
  );
}
