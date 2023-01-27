import { useQuery } from '@tanstack/react-query';
import { getAbandonedReviewAPI } from 'apis/AbandonedDogApi';
import React from 'react';
import ReviewItem from './ReviewItem';
import styles from './ReviewList.module.scss';
export default function ReviewList() {
  const { data, isLoading } = useQuery({ queryKey: ['abandonedReview'], queryFn: getAbandonedReviewAPI });
  if (isLoading) return;

  return (
    <div className={styles.list}>
      {data.map(item => (
        <ReviewItem key={item.id} item={item} />
      ))}
    </div>
  );
}
