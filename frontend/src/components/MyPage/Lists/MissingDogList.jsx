import { useQuery } from '@tanstack/react-query';
import { getMissingDogListOfUser } from 'apis/api/report';
import React from 'react';
import styles from './MissingDogList.module.scss';
import MissingDogItem from '../Items/MissingDogItem';

export default function MissingDog({ userId }) {
  const { data, isLoading } = useQuery({
    queryKey: ['myMissingDogList'],
    queryFn: () => getMissingDogListOfUser(userId, 20, 0),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      {data.length === 0 ? (
        <div className={styles['no-data']}>등록된 실종 공고가 없습니다.</div>
      ) : (
        data.map(item => <MissingDogItem key={item.missingId} item={item} />)
      )}
    </div>
  );
}
