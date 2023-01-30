import { useQuery } from '@tanstack/react-query';
import { getMissingList } from 'apis/api/report';
import React from 'react';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
export default function MissingDogList() {
  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingList(0, 6, 0),
  });

  if (isLoading) return;

  return (
    <div>
      <div className={styles.list}>
        {data.map(item => (
          <MissingDogItem key={item.missingId} item={item} />
        ))}
      </div>
    </div>
  );
}
