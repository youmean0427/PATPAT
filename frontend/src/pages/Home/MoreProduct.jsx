import { useQuery } from '@tanstack/react-query';
import { getProtectList } from 'apis/api/protect';
import AbandonedDogItem from 'components/Common/Home/AbandonedDogItem';
import ShelterContainer from 'containers/ShelterContainer';
import React, { useState } from 'react';
import styles from './MoreProduct.module.scss';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';

export default function MoreProduct() {
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery({
    queryKey: ['protectListSortedByEuthanasia', page],
    queryFn: () => getProtectList(0, 0, LIMIT, page - 1),
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return (
    <ShelterContainer title="보호 동물 전체 보기">
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
        {data?.list?.map(item => {
          return <AbandonedDogItem key={item.protectId} item={item} />;
        })}
      </div>
      <button onClick={() => setPage(prev => prev + 1)}>더 보기</button>
    </ShelterContainer>
  );
}
