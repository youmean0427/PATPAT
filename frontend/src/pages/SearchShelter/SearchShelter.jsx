import React from 'react';
import styles from './SearchShelter.module.scss';
import SelectMap from 'components/Common/Map/SelectMap';
import ShelterList from 'components/SearchShelter/ShelterList';
import { useQuery } from '@tanstack/react-query';
import { getShelterList, getSidoList } from 'apis/api/shelter';
import Banner from 'components/Common/Banner/Banner';
import ShelterSearchBar from 'components/SearchShelter/ShelterSearchBar';
import { useRecoilValue } from 'recoil';
import { selectBreedState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';

export default function SearchShelter() {
  // const sido = useRecoilValue(selectSidoState);
  // const gugun = useRecoilValue(selectGugunState);
  // const breed = useRecoilValue(selectBreedState);
  const { data: sidoList, isLoading } = useQuery(['sidoList'], getSidoList);
  // const { data: shelterList, isLoading: shelterIsLoading } = useQuery(['shelterList'], () => {
  //   getShelterList(breed.breedId, gugun.gugunCode, sido.sidoCode);
  // });

  if (isLoading) return;
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
