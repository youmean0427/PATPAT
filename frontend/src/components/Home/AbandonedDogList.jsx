import { useQuery } from '@tanstack/react-query';
import { getProtectList } from 'apis/api/protect';
import React from 'react';
import AbandonedDogItem from './AbandonedDogItem';
import styles from './AbandonedDogList.module.scss';
export default function AbandonedDogList() {
  const { isLoading, data } = useQuery({
    queryKey: ['protectListSortedByEuthanasia'],
    queryFn: () => getProtectList(0, 4, 0),
  });
  if (isLoading) return;
  return (
    <div className={styles.list}>
      {data.map(item => (
        <AbandonedDogItem key={item.id} item={item} />
      ))}
    </div>
  );
}
