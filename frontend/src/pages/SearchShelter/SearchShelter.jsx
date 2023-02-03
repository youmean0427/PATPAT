import React from 'react';
import styles from './SearchShelter.module.scss';
import SelectMap from 'components/Common/Map/SelectMap';
import ShelterList from 'components/SearchShelter/ShelterList';
import { useQuery } from '@tanstack/react-query';
import { getSidoList } from 'apis/api/shelter';

export default function SearchShelter() {
  const { data: sidoList, isLoading, isError } = useQuery(['sidoList'], getSidoList);
  if (isLoading) return;
  return (
    <div className={styles.wrap}>
      <SelectMap list={sidoList} />
      <ShelterList />
    </div>
  );
}
