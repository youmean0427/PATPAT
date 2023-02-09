import { useQuery } from '@tanstack/react-query';
import { getMissingDogList } from 'apis/api/report';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import Button from '@mui/material/Button';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList } from 'utils/changeSelectTemplate';
import { useEffect } from 'react';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { MdArrowBackIosNew, MdArrowForwardIos } from 'react-icons/md';

export default function MissingDogList({ genderCode, breedCode, pages, totalPageFunction }) {
  // const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList', breedCode.value, genderCode.value, pages],
    queryFn: () => getMissingDogList(breedCode.value, genderCode.value, LIMIT, pages - 1),
  });

  // const handleClickPrev = () => {
  //   setPage(prev => prev - 1);
  // };

  // const handleClickNext = () => {
  //   setPage(prev => prev + 1);
  // };

  // console.log(data);
  if (isLoading) return;
  totalPageFunction(data.totalPage);
  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}>
          {/* <div className={styles.pagination}>
            <button
              onClick={handleClickPrev}
              className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
              disabled={page === 1 ? true : false}
            >
              <MdArrowBackIosNew />
            </button>
            <button
              onClick={handleClickNext}
              className={page === data.totalPage ? `${styles.button} ${styles.disabled}` : styles.button}
              disabled={page === data.totalPage ? true : false}
            >
              <MdArrowForwardIos />
            </button>
          </div> */}
        </div>
      </div>
      <div className={styles.container}>
        <div className={styles.list}>
          {data.list.map((item, index) => (
            <MissingDogItem key={index} item={item} />
          ))}
        </div>
      </div>
      <div className={styles['container-newButton']}>
        <Navbar>
          <MenuLink move="create" value="글쓰기" />
        </Navbar>
      </div>
    </div>
  );
}
