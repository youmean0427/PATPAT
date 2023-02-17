import { useQuery } from '@tanstack/react-query';
import { getShelterConsultations } from 'apis/api/consulting';
import React from 'react';
import styles from './ConsultingList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';
import { isTodayConsulting } from 'utils/isTodayConsulting';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import { useState } from 'react';

export default function ConsultingList({ stateCode, shelterId }) {
  const [page, setPage] = useState(1);
  const LIMIT = 9;
  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  const { data, isLoading } = useQuery({
    queryKey: ['getConsultations', stateCode, page],
    queryFn: () => {
      return getShelterConsultations(shelterId, LIMIT, page - 1, stateCode);
    },
  });
  // click : 0 전체 , 1 대기 , 2 승인 , 3 오늘날짜 전체

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
        {data.list.map(item => (
          <ConsultingItem key={item.consultingId} item={item} filterCode={stateCode} />
        ))}
      </div>
    </>
  );
}
