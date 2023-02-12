import { useQuery } from '@tanstack/react-query';
import { getProtectList } from 'apis/api/protect';
import React from 'react';
import AbandonedDogItem from './AbandonedDogItem';
import styles from './AbandonedDogList.module.scss';
export default function AbandonedDogList() {
  const { data } = useQuery({
    queryKey: ['protectListSortedByEuthanasia'],
    queryFn: () => getProtectList(0, 0, 4, 0),
  });
  return (
    <div className={styles.list}>
      {data?.list?.map(item => (
        <AbandonedDogItem key={item.protectId} item={item} />
      ))}
    </div>
  );
}
