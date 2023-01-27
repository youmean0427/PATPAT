import { useQuery } from '@tanstack/react-query';
import { getAbandonedDogsAPI } from 'apis/AbandonedDogApi';
import React from 'react';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
export default function MissingDogList() {
  const { isLoading, data } = useQuery({ queryKey: ['abandonedDogList'], queryFn: getAbandonedDogsAPI });
  if (isLoading) return;
  return (
    <div className={styles.list}>
      {data.map(item => (
        <MissingDogItem key={item.id} item={item} />
      ))}
    </div>
  );
}
