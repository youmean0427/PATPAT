import Banner from 'components/Common/Banner/Banner';
import MissingDogList from 'components/Report/MissingDog/MissingDogList';
import PersonalDogList from 'components/Report/PersonalDog/PersonalDogList';
import React from 'react';
import { useState } from 'react';
import styles from './Report.module.scss';
import { getBreedsList } from 'apis/api/shelter';
import { useQuery } from '@tanstack/react-query';
import Select from 'react-select';

import { changeBreedList, changeReportBreedList } from 'utils/changeSelectTemplate';

export default function Report() {
  const { isLoading, data: breedList } = useQuery({ queryKey: ['getBreedsList'], queryFn: () => getBreedsList() });

  const [selected, setSelected] = useState('실종');
  const handleClick = value => {
    setSelected(value);
  };

  const breed = [{ value: 0, label: '전체 보기' }];
  const gender = [
    { value: 0, label: '성별' },
    { value: 1, label: '수컷' },
    { value: 2, label: '암컷' },
  ];

  const [selectedBreed, setSelectedBreed] = useState(breed[0]);
  const [selectedGender, setSelectedGender] = useState(gender[0]);

  if (isLoading) return;

  return (
    <div>
      <Banner title="실종견 / 임보견" />

      <div className={styles.container}>
        <button
          variant={selected === '실종' ? 'contained' : 'outlined'}
          onClick={() => handleClick('실종')}
          className={selected === '실종' ? styles['main-button-active'] : styles['main-button']}
        >
          실종견
        </button>
        {/* </button> */}
        <button
          variant={selected === '임보' ? 'contained' : 'outlined'}
          onClick={() => handleClick('임보')}
          className={selected === '임보' ? styles['main-button-active'] : styles['main-button']}
        >
          임보견
        </button>
      </div>

      <hr />
      <div className={styles['container-search-inner']}>
        <div>
          <span>
            <Select
              options={changeReportBreedList(breedList)}
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
        {/* <div className={styles.pagination}>
          <button
            onClick={handleClickPrev}
            className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
            disabled={page === 1 ? true : false}
          >
            <MdArrowBackIosNew />
          </button>
          <button
            onClick={handleClickNext}
            className={page === totalPage ? `${styles.button} ${styles.disabled}` : styles.button}
            disabled={page === totalPage ? true : false}
          >
            <MdArrowForwardIos />
          </button>
        </div> */}
      </div>

      <div>
        {selected === '실종' ? (
          <MissingDogList
            genderCode={selectedGender}
            breedCode={selectedBreed}
            // pages={page}
            // totalPageFunction={totalPageFunction}
          />
        ) : null}
        {selected === '임보' ? (
          <PersonalDogList
            genderCode={selectedGender}
            breedCode={selectedBreed}
            // pages={page}
            // totalPageFunction={totalPageFunction}
          />
        ) : null}
      </div>
    </div>
  );
}
