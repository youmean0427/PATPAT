import { useQuery } from '@tanstack/react-query';
import { getShelterList } from 'apis/api/shelter';
import React from 'react';
import { useRecoilValue } from 'recoil';
import { selectBreedState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';
import ShelterItem from './ShelterItem';
import styles from './ShelterList.module.scss';

function ShelterList() {
  const sido = useRecoilValue(selectSidoState);
  const gugun = useRecoilValue(selectGugunState);
  const breed = useRecoilValue(selectBreedState);
  const { data, isLoading } = useQuery(
    ['shelterList', sido.sidoCode, gugun.gugunCode, breed.breedId],
    () => {
      return getShelterList(sido.sidoCode, gugun.gugunCode, breed.breedId, 12, 0);
    },
    { staleTime: 1000 * 60 * 5 }
  );
  if (isLoading) return;
  console.log(data);
  return (
    <div className={styles.container}>
      {data.length !== 0 ? (
        data.map((item, index) => {
          return <ShelterItem key={index} item={item} />;
        })
      ) : (
        <span>검색 결과가 없습니다.</span>
      )}
    </div>
  );
}

export default React.memo(ShelterList);
