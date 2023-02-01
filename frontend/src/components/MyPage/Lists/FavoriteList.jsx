import { useQuery } from '@tanstack/react-query';
import { getFavListListOfUser } from 'apis/api/user';
import React from 'react';
import styles from './FavoriteList.module.scss';
import FavoriteItem from '../Items/FavoriteItem';

export default function FavoriteList() {
  const { data, isLoading } = useQuery({
    queryKey: ['myFavoriteList'],
    queryFn: () => getFavListListOfUser(),
  });

  if (isLoading) return;

  console.log(data);
  return (
    <div className={styles.container}>
      {data.map(item => (
        <FavoriteItem key={item.dogId} item={item} />
      ))}
    </div>
  );
}
