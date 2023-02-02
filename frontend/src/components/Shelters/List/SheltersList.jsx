import { useQuery } from '@tanstack/react-query';
import { getShelterList } from 'apis/api/shelter';
import React from 'react';
import styles from './SheltersList.module.scss';
import SheltersItem from '../Items/SheltersItem';

export default function SheltersList({ sido, gugun, breed }) {
  const { data, isLoading } = useQuery({
    queryKey: ['searchAllShelters'],
    queryFn: () => getShelterList(sido, gugun, breed),
  });

  if (isLoading) return;
  console.log(data);

  return (
    <div className={styles.container}>
      {data.map(item => (
        <SheltersItem key={item.shelterId} item={item} sido={sido} gugun={gugun} breed={breed} />
      ))}
    </div>
  );
}
