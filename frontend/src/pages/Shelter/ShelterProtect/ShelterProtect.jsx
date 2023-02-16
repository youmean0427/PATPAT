import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getProtectListOfShelter } from 'apis/api/protect';
import { checkMyShelter } from 'utils/checkMyShelter';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import ShelterProtectUser from './ShelterProtectUser';
import ShelterProtectManager from './ShelterProtectManager';
export default function ShelterProtect() {
  const {
    state: { shelterId },
  } = useLocation();
  const [page, setPage] = useState(1);
  const LIMIT = 8;
  const myShelterId = useRecoilValue(myShelterIdState);
  const { data, isLoading } = useQuery(['protectListOfShelter', shelterId, page], () => {
    return getProtectListOfShelter(shelterId, 0, LIMIT, page - 1);
  });
  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return checkMyShelter(shelterId, myShelterId) ? (
    <ShelterProtectManager shelterId={shelterId} />
  ) : (
    <ShelterProtectUser
      handleClickNext={handleClickNext}
      handleClickPrev={handleClickPrev}
      data={data}
      page={page}
      shelterId={shelterId}
      myShelterId={myShelterId}
    />
  );
}
