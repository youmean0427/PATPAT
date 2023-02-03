import { useQuery } from '@tanstack/react-query';
import { getGugunList } from 'apis/api/shelter';
import React from 'react';
import styles from './SelectList.module.scss';
import GugunItem from '../Items/GugunItem';

export default function GugunList({ setGugun, sido }) {
  const { data, isLoading } = useQuery({
    queryKey: ['searchAllGuguns'],
    queryFn: () => getGugunList(sido),
  });
  const handleBreeds = e => {
    setGugun(e.target.value);
  };

  if (isLoading) return;

  return (
    <div className={styles['select-list']}>
      <input type="text" list="gugun" placeholder="구군 검색" className={styles['select-box']} />
      <datalist onChange={handleBreeds} id="gugun" className={styles['select-box']}>
        {data.map((item, idx) => (
          <GugunItem key={item.code} item={item.name} />
        ))}
      </datalist>
    </div>
  );
}
