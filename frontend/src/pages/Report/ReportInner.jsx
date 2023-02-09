import Banner from 'components/Common/Banner/Banner';
import MissingDogList from 'components/Report/MissingDog/MissingDogList';
import PersonalDogList from 'components/Report/PersonalDog/PersonalDogList';
import React from 'react';
import { useState } from 'react';
import styles from './Report.module.scss';
import Button from '@mui/material/Button';
import { getBreedsList } from 'apis/api/shelter';
import { useQuery } from '@tanstack/react-query';
import Select from 'react-select';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { getMissingDogList } from 'apis/api/report';
import { useEffect } from 'react';

export default function ReportInner({ genderCode }) {
  console.log(genderCode, '젠더코드');

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList', genderCode],
    queryFn: () => getMissingDogList(0, genderCode, 100, 0),
  });

  useEffect(() => {}, [genderCode]);

  if (isLoading) return;

  return (
    <div>
      <MissingDogList item={data} />
    </div>
  );
}
