import { useQuery } from '@tanstack/react-query';
import { getBreedsList } from 'apis/api/shelter';
import React from 'react';
import styles from './SelectList.module.scss';
import BreedsItem from '../Items/BreedsItem';

export default function BreedsList({ setBreed }) {
  const { data, isLoading } = useQuery({
    queryKey: ['searchAllBreeds'],
    queryFn: () => getBreedsList(),
  });
  const handleBreeds = e => {
    setBreed(e.target.value);
  };

  if (isLoading) return;

  return (
    <div className={styles['select-list']}>
      <input type="text" list="list" placeholder="견종 검색" className={styles['select-box']} />
      <datalist onChange={handleBreeds} id="list" className={styles['select-box']}>
        {data.map((item, idx) => (
          <BreedsItem key={item.breedId} item={item.breedName} />
        ))}
      </datalist>
    </div>
  );
}
