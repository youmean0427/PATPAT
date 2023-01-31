import { useQuery } from '@tanstack/react-query';
import { getCountShelterByBreed } from 'apis/api/shelter';
import MbtiContainer from 'components/Common/MbtiContainer';
import SouthKorea from 'components/Common/SouthKorea';
import React from 'react';
import { useLocation } from 'react-router';

export default function MoreInfo() {
  const {
    state: { breedId, breedName },
  } = useLocation();
  const { data, isLoading } = useQuery(['searchBreedCountPerRegion'], () => getCountShelterByBreed(breedId));
  if (isLoading) return;
  return (
    <MbtiContainer>
      <h1>
        전국에 {breedName}이 있는 보호소는 총 {data.totalCnt}개 입니다.
      </h1>
      <SouthKorea list={data.list} />
    </MbtiContainer>
  );
}
