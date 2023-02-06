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

  return (
    <div className={styles.container}>
      {data.length === 0 ? (
        <div className={styles['no-data']}>등록된 '꾹' 정보가 없습니다.</div>
      ) : (
        data.map(item => <FavoriteItem key={item.dogId} item={item} />)
      )}
    </div>
  );
}
