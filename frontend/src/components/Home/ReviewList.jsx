import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React from 'react';
import ReviewItem from './ReviewItem';
import styles from './ReviewList.module.scss';
export default function ReviewList() {
  const { data, isLoading } = useQuery({
    queryKey: ['abandonedReview'],
    queryFn: () => getBoardList(0, 4, 0),
  });
  if (isLoading) return;

  return (
    <div className={styles.list}>
      {data.map(item => (
        <ReviewItem key={item.id} item={item} />
      ))}
    </div>
  );
}
