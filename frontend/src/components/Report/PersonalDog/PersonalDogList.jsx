import { useQuery } from '@tanstack/react-query';
import { getPersonalDogList } from 'apis/api/report';
import React, { useState } from 'react';
import PersonalDogItem from './PersonalDogItem';
import styles from './PersonalDogList.module.scss';
export default function PersonalDogList() {
  const [selectedGender, setSelectedGender] = useState(localStorage.getItem('selectedGender') || '');
  const [selectedBreed, setSelectedBreed] = useState(localStorage.getItem('selectedBreed') || '');

  const { isLoading, data } = useQuery({
    queryKey: ['personalDogList'],
    queryFn: () => getPersonalDogList(selectedBreed, selectedGender, 6, 0),
  });
  const handleGenderChange = event => {
    setSelectedGender(event.target.value);
    localStorage.setItem('selectedGender', event.target.value);
  };
  const handleBreedChange = event => {
    setSelectedBreed(event.target.value);
    localStorage.setItem('selectedBreed', event.target.value);
  };

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
              <option value={0}>수컷</option>
              <option value={1}>암컷</option>
            </select>
          </span>
        </div>
      </div>

      <div className={styles.list}>
        {data.map(item => (
          <PersonalDogItem key={item.personalProtectionId} item={item} />
        ))}
      </div>
    </div>
  );
}
