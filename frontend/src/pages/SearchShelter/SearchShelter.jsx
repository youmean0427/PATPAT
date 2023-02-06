import React, { Suspense } from 'react';
import styles from './SearchShelter.module.scss';
import SelectMap from 'components/Common/Map/SelectMap';
import ShelterList from 'components/SearchShelter/ShelterList';
import { useQuery } from '@tanstack/react-query';
import { getSidoList } from 'apis/api/shelter';
import Banner from 'components/Common/Banner/Banner';
import ShelterSearchBar from 'components/SearchShelter/ShelterSearchBar';

export default function SearchShelter() {
  const { data: sidoList, isLoading: isLoadingSido } = useQuery(['sidoList'], getSidoList);

  if (isLoadingSido) return;
  return (
    <div className={styles.wrap}>
      <Banner title="보호소 찾기" />
      <div className={styles.content__inner}>
        <SelectMap list={sidoList} />
        <div className={styles.search__wrap}>
          <div className={styles.search__inner}>
            <ShelterSearchBar />
            <ShelterList />
          </div>
        </div>
      </div>
    </div>
  );
}
