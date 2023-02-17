import { useQuery } from '@tanstack/react-query';
import { getVolScheduleListOfShelter } from 'apis/api/volunteer';
import React from 'react';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import styles from './ShelterVolNoticeList.module.scss';
import ShelterVolNoticeItem from './ShelterVolNoticeItem';
export default function ShelterVolNoticeList() {
  const [page, setPage] = useState(1);
  const { shelterId } = useParams();
  const LIMIT = 9;
  const { data, isLoading } = useQuery(['volScheduleListofShelter'], () =>
    getVolScheduleListOfShelter(shelterId, LIMIT, page - 1)
  );
  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return (
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
            !data || data.totalPage === 0 || page === data.totalPage
              ? `${styles.button} ${styles.disabled}`
              : styles.button
          }
          disabled={!data || data.totalPage === 0 || page === data.totalPage ? true : false}
        >
          <MdArrowForwardIos />
        </button>
      </div>
      <div className={styles.list}>
        {data?.list?.map(item => (
          <ShelterVolNoticeItem key={item.scheduleId} item={item} />
        ))}
      </div>
    </>
  );
}
