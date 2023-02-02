import { useQuery } from '@tanstack/react-query';
import { getMissingDogList } from 'apis/api/report';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

export default function MissingDogList() {
  const [selectedGender, setSelectedGender] = useState(localStorage.getItem('selectedGender') || '');
  const [selectedBreed, setSelectedBreed] = useState(localStorage.getItem('selectedBreed') || '');

  const handleGenderChange = event => {
    setSelectedGender(event.target.value);
    localStorage.setItem('selectedGender', event.target.value);
  };
  const handleBreedChange = event => {
    setSelectedBreed(event.target.value);
    localStorage.setItem('selectedBreed', event.target.value);
  };

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingDogList(selectedBreed, selectedGender, 0, 0),
  });

  if (isLoading) return;
  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}>
          <span>
            <select onChange={handleBreedChange} value={selectedBreed}>
              <option value={111}>웰시</option>
              <option value={222}>푸들</option>
              <option value={333}>불독</option>
              <option value={444}>가나다라마바사</option>
            </select>
          </span>
          <span>
            <select onChange={handleGenderChange} value={selectedGender}>
              <option>전체</option>
              <option value={0}>수컷</option>
              <option value={1}>암컷</option>
            </select>
          </span>
        </div>
      </div>
      <div className={styles.container}>
        <div className={styles.list}>
          {data.map(item => (
            <MissingDogItem key={item.missingId} item={item} />
          ))}
        </div>
      </div>
      <div>
        <Link to="create">
          <button className={styles.searchButton}>글쓰기</button>
        </Link>
      </div>
    </div>
  );
}
