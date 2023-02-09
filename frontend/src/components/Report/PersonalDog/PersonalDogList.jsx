import { useQuery } from '@tanstack/react-query';
import { getPersonalDogList } from 'apis/api/report';
import PersonalDogItem from './PersonalDogItem';
import styles from './PersonalDogList.module.scss';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import Button from '@mui/material/Button';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList } from 'utils/changeSelectTemplate';
import { useEffect } from 'react';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { MdArrowBackIosNew, MdArrowForwardIos } from 'react-icons/md';

export default function PersonalDogList({ genderCode, breedCode, pages, totalPageFunction }) {
  const LIMIT = 8;

  const { isLoading, data } = useQuery({
    queryKey: ['personalDogList', breedCode.value, genderCode.value, pages],
    queryFn: () => getPersonalDogList(breedCode.value, genderCode.value, LIMIT, pages - 1),
  });
  console.log(data);
  if (isLoading) return;

  return (
    <div>
      <div className={styles['container-search']}>
        <div className={styles['container-search-inner']}></div>
      </div>
      <div className={styles.container}>
        <div className={styles.list}>
          {data.list.map((item, index) => (
            <PersonalDogItem key={index} item={item} />
          ))}
        </div>
      </div>
      <div className={styles['container-newButton']}>
        <Navbar>
          <MenuLink move="create" value="글쓰기" />
        </Navbar>
      </div>
    </div>
  );
}
