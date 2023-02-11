import { useQuery } from '@tanstack/react-query';
import { getMyConsultations } from 'apis/api/consulting';
import React, { useState } from 'react';
import styles from './ReservationList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';

export default function ConsultingList({ userId }) {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery({
    queryKey: ['myConsultingList'],
    queryFn: () => getMyConsultations(LIMIT, page - 1, userId),
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
          <div className={styles['no-data']}>신청한 상담 정보가 없습니다.</div>
        ) : (
          data.list.map(item => <ConsultingItem key={item.consultingId} item={item} />)
        )}
      </div>
    </div>
  );
}
