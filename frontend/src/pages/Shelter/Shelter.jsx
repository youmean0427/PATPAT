import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { useParams } from 'react-router';
import styles from './Shelter.module.scss';
import Content from 'components/ShelterPage/Content';

export default function Shelter() {
  const params = useParams();
  const shelterId = params.shelterId;

  const { data, isLoading } = useQuery({
    queryKey: ['getShelterDetail'],
    queryFn: () => getShelterDetail(shelterId),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      <div className={styles['main-info']}>
        <img className={styles['main-img']} src={data[0].fileDto.origFilename} alt="" />
        <div className={styles['main-content']}>
          <p className={styles['shelter-name']}>{data[0].name}</p>
          <p className={styles['shelter-info']}>{data[0].name}에 방문해주셔서 감사합니다.</p>
        </div>
        <hr className={styles.line} />
      </div>
      <Content />
    </div>
  );
}
