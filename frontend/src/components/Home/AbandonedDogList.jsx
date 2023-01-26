import { useQuery } from '@tanstack/react-query';
import { getAbandonedDogsAPI } from 'apis/AbandonedDogApi';
import React from 'react';
import AbandonedDogItem from './AbandonedDogItem';
import styles from './AbandonedDogList.module.scss';
export default function AbandonedDogList() {
  const { isLoading, data } = useQuery({ queryKey: ['abandonedDogList'], queryFn: getAbandonedDogsAPI });
  if (isLoading) return;
  return (
    <div className={styles.list}>
      {data.map(item => (
        <AbandonedDogItem key={item.id} item={item} />
      ))}
    </div>
  );
}
