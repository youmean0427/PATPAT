import { useQuery } from '@tanstack/react-query';
import { getPersonalDogList } from 'apis/api/report';
import PersonalDogItem from './PersonalDogItem';
import styles from './PersonalDogList.module.scss';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import Button from '@mui/material/Button';

export default function PersonalDogList() {
  const [selectedGender, setSelectedGender] = useState(0);
  const [selectedBreed, setSelectedBreed] = useState(0);

  const { isLoading, data } = useQuery({
    queryKey: ['personalDogList'],
    queryFn: () => getPersonalDogList(selectedBreed, selectedGender, 10, 0),
  });

  // Data
  const breed = [
    { value: 0, label: '견종' },
    { value: 1, label: '웰시코기' },
    { value: 2, label: '푸들' },
  ];
  const gender = [
    { value: 0, label: '성별' },
    { value: 1, label: '수컷' },
    { value: 2, label: '암컷' },
  ];

  // console.log(selectedBreed);
  // console.log(selectedGender);

  if (isLoading) return;

  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}>
          <span>
            <Select
              options={breed}
              className={styles['select-breed']}
              onChange={setSelectedBreed}
              defaultValue={breed[0]}
            />
          </span>
          <span>
            <Select
              options={gender}
              className={styles['select-gender']}
              onChange={setSelectedGender}
              defaultValue={gender[0]}
            />
          </span>
        </div>
      </div>
      <div className={styles.container}>
        <div className={styles.list}>
          {data.map((item, index) => (
            <PersonalDogItem key={index} item={item} />
          ))}
        </div>
      </div>
      <div className={styles['container-newButton']}>
        <Link to="create">
          <Button variant="contained" className={styles.newButton}>
            글쓰기
          </Button>
        </Link>
      </div>
    </div>
  );
}
