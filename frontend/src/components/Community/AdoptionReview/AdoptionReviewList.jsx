import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React from 'react';
import AdoptionReviewItem from './AdoptionReviewItem';
import styles from './AdoptionReviewList.module.scss';

export default function AdoptionReviewList() {
  const { isLoading, data } = useQuery({
    queryKey: ['adoptionList'],
    queryFn: () => getBoardList(0, 4, 0),
  });
  if (isLoading) return; // isLoading = false, data = 데이터 리스트(입양 후기)

  return (
    <div className={styles.list}>
      {data.map(item => (
        <AdoptionReviewItem key={item.id} item={item} />
      ))}
    </div>
  );
}
