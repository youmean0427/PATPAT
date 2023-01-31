import { useQuery } from '@tanstack/react-query';
import { getPersonalDogList } from 'apis/api/report';
import React from 'react';
import PersonalDogItem from './PersonalDogItem';
import styles from './PersonalDogList.module.scss';
export default function PersonalDogList() {
  const { isLoading, data } = useQuery({
    queryKey: ['personalDogList'],
    queryFn: () => getPersonalDogList(0, 6, 0),
  });
  if (isLoading) return;
  return (
    <div className={styles.list}>
      {data.map(item => (
        <PersonalDogItem key={item.personalProtectionId} item={item} />
      ))}
    </div>
  );
}
