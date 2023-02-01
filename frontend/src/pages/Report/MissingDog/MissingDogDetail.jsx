import { getMissingDogDetail } from 'apis/api/report';
import React from 'react';
import { useLocation } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import styles from './MissingDogDetail.module.scss';
import MissingDogDetailContent from 'components/Report/MissingDog/MissingDogDetailContent';

export default function MissingDogDetail() {
  const location = useLocation();
  const item = location.state.missingId;

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingDogDetail(item),
  });

  if (isLoading) return;
  return (
    <div>
      <div className={styles.title}>실종견/임보견 상세</div>
      <hr />
      <MissingDogDetailContent data={data} />
    </div>
  );
}
