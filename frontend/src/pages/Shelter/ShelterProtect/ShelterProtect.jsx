import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getProtectListOfShelter } from 'apis/api/protect';
import AbandonedDogItem from 'components/Home/AbandonedDogItem';
import styles from './ShelterProtect.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
export default function ShelterProtect() {
  const {
    state: { shelterId },
  } = useLocation();
  const [page, setPage] = useState(0);

  const LIMIT = 8;

  const { data, isLoading } = useQuery(['protectListOfShelter', shelterId, page], () => {
    return getProtectListOfShelter(shelterId, LIMIT, page);
  });
  const handleClickPrev = () => {
    console.log('prev');
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    console.log('next');
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  console.log(data);
  return (
    <ShelterContainer title="보호 동물">
      <div className={styles.pagination}>
        <button
          onClick={handleClickPrev}
          className={page === 0 ? `${styles.button} ${styles.disabled}` : styles.button}
          disabled={page === 0 ? true : false}
        >
          <MdArrowBackIosNew />
        </button>
        <button
          onClick={handleClickNext}
          className={page === 10 ? `${styles.button} ${styles.disabled}` : styles.button}
          disabled={page === 10 ? true : false}
        >
          <MdArrowForwardIos />
        </button>
      </div>
      <div className={styles.list}>
        {data.map(item => {
          return <AbandonedDogItem key={item.protectId} item={item} />;
        })}
      </div>
    </ShelterContainer>
  );
}
