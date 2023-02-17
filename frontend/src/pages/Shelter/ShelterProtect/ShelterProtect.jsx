import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { useLoaderData, useLocation } from 'react-router-dom';
import { getProtectListOfShelter } from 'apis/api/protect';
import { checkMyShelter } from 'utils/checkMyShelter';
import { useRecoilState, useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import ShelterProtectUser from './ShelterProtectUser';
import { selectShelterBreedState } from 'recoil/atoms/shelter';

export default function ShelterProtect() {
  const breedData = useLoaderData();
  const breed = useRecoilValue(selectShelterBreedState);
  const {
    state: { shelterId },
  } = useLocation();
  const [page, setPage] = useState(1);
  const LIMIT = 8;
  const myShelterId = useRecoilValue(myShelterIdState);
  const { data, isLoading } = useQuery(['protectListOfShelter', breed.breedId, shelterId, page], () => {
    return getProtectListOfShelter(shelterId, 0, breed.breedId, LIMIT, page - 1);
  });
  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return (
    <ShelterProtectUser
      handleClickNext={handleClickNext}
      handleClickPrev={handleClickPrev}
      data={data}
      page={page}
      setPage={setPage}
      shelterId={shelterId}
      myShelterId={myShelterId}
      breedData={breedData}
    />
  );
}
