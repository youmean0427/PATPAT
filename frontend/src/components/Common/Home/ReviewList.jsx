import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState } from 'react';
import ReviewItem from './ReviewItem';
import styles from './ReviewList.module.scss';
import AdoptionReviewItem from 'components/Community/items/AdoptionReviewItem';

export default function ReviewList() {
  const [isChange, setIsChange] = useState(false);
  const { data, isLoading } = useQuery({
    queryKey: ['abandonedReview'],
    queryFn: () => getBoardList(0, 4, 0),
  });
  if (isLoading) return;
  return (
    <div className={styles.list}>
      {data?.list?.map(item => (
        <AdoptionReviewItem key={item.boardId} item={item} change={setIsChange} />
      ))}
    </div>
  );
}
