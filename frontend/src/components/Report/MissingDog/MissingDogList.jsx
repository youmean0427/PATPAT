import { useQuery } from '@tanstack/react-query';
import { getMissingDogList } from 'apis/api/report';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import Button from '@mui/material/Button';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList } from 'utils/changeSelectTemplate';

export default function MissingDogList() {
  const breedData = useQuery({
    queryKey: ['getBreedsList'],
    queryFn: () => getBreedsList(),
  });
  const breedList = breedData.data;

  const breed = [{ value: 0, label: '견종' }];
  const gender = [
    { value: 0, label: '성별' },
    { value: 1, label: '수컷' },
    { value: 2, label: '암컷' },
  ];
  const [selectedGender, setSelectedGender] = useState(gender[0]);
  const [selectedBreed, setSelectedBreed] = useState(breed[0]);
  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingDogList(selectedBreed.value, selectedGender.value, 2, 0),
  });

  if (isLoading) return;

  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}>
          <span>
            <Select
              options={changeBreedList(breedList)}
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
            <MissingDogItem key={index} item={item} />
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
