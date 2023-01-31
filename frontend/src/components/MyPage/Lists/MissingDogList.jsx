import { useQuery } from '@tanstack/react-query';
import { getMissingDogListOfUser } from 'apis/api/report';
import React from 'react';
import styles from './MissingDog.module.scss';
import MissingDogItem from '../Items/MissingDogItem';

export default function MissingDog() {
  const { data, isLoading } = useQuery({
    queryKey: ['myFavoriteList'],
    queryFn: () => getMissingDogListOfUser(),
  });

  if (isLoading) return;

  console.log(data);
  return (
    <div className={styles.container}>
      {data.map(item => (
        <MissingDogItem key={item.dogId} item={item} />
      ))}
    </div>
  );
}
