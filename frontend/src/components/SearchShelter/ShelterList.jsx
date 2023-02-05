import { useQuery } from '@tanstack/react-query';
import { getShelterList } from 'apis/api/shelter';
import React from 'react';
import { useRecoilValue } from 'recoil';
import { selectBreedState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';
import ShelterItem from './ShelterItem';
import styles from './ShelterList.module.scss';

const list = [
  { shelterId: 1, name: '초량 보호소' },
  { shelterId: 2, name: '초량 보호소' },
  { shelterId: 3, name: '초량 보호소' },
  { shelterId: 4, name: '초량 보호소' },
  { shelterId: 5, name: '초량 보호소' },
  { shelterId: 6, name: '초량 보호소' },
  { shelterId: 7, name: '초량 보호소' },
  { shelterId: 8, name: '초량 보호소' },
  { shelterId: 9, name: '초량 보호소' },
  { shelterId: 10, name: '초량 보호소' },
  { shelterId: 11, name: '초량 보호소' },
  { shelterId: 12, name: '초량 보호소' },
];
function ShelterList() {
  const { sidoCode } = useRecoilValue(selectSidoState);
  const { gugunCode } = useRecoilValue(selectGugunState);
  const { breedId } = useRecoilValue(selectBreedState);
  const { data, isLoading } = useQuery(['shelterList', sidoCode, gugunCode, breedId], () => {
    return getShelterList(sidoCode, gugunCode, breedId, 4, 0);
  });
  if (isLoading) return;
  return (
    <div className={styles.container}>
      {list.map((item, index) => {
        return <ShelterItem key={index} item={item} />;
      })}
    </div>
  );
}

export default React.memo(ShelterList);
