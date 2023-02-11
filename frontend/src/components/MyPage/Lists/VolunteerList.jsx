import { useQuery } from '@tanstack/react-query';
import { getVolReservationOfUser } from 'apis/api/volunteer';
import React, { useState } from 'react';
import styles from './ReservationList.module.scss';
import VolunteerItem from '../Items/VolunteerItem';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';

export default function VolunteerList({ userId }) {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery({
    queryKey: ['myVolunteerList'],
    queryFn: () => getVolReservationOfUser(LIMIT, page - 1, userId),
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;

  return (
    <div>
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
      <div className={styles.container}>
        {data.totalCount === 0 ? (
          <div className={styles['no-data']}>신청한 봉사 정보가 없습니다.</div>
        ) : (
          data.list.map(item => <VolunteerItem key={item.reservationId} item={item} />)
        )}
      </div>
    </div>
  );
}
