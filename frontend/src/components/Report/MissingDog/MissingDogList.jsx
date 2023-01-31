import { useQuery } from '@tanstack/react-query';
import { getMissingDogList } from 'apis/api/report';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
import React, { useState } from 'react';
export default function MissingDogList() {
  const [genderIndex, setGenderIndex] = useState(null);

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingDogList(null, genderIndex, 1, 0),
  });
  if (isLoading) return;

  return (
    <div>
      <select>
        <option>웰시</option>
        <option>푸들</option>
        <option>불독</option>
      </select>
      <select onChange={e => setGenderIndex(e.target.value)}>
        <option value={0}>수컷</option>
        <option value={1}>암컷</option>
      </select>

      <div className={styles.container}>
        <div className={styles.list}>
          {data.map(item => (
            <MissingDogItem key={item.missingId} item={item} />
          ))}
        </div>
      </div>
    </div>
  );
}
