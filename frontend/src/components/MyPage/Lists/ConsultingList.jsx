import { useQuery } from '@tanstack/react-query';
import { getMyConsultations } from 'apis/api/consulting';
import React, { useState } from 'react';
import ConsultingItem from '../Items/ConsultingItem';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import styles from './ConsultingList.module.scss';
import NoData from 'components/Common/NoData';

export default function ConsultingList({ stateCode }) {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };

  const { data, isLoading } = useQuery({
    queryKey: ['getMyConsultations', stateCode],
    queryFn: () => {
      return getMyConsultations(LIMIT, page - 1, stateCode);
    },
  });

  if (isLoading) return;
  return (
    <div>
      {data.totalCount === 0 ? (
        <NoData>신청된 상담 내역이 없습니다.</NoData>
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
            {data.list.map(item => (
              <ConsultingItem key={item.consultingId} item={item} filterCode={stateCode} />
            ))}
          </div>
        </>
      )}
    </div>
  );
}
