import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import SearchVolunteerItem from './SearchVolunteerItem';
import styles from './SearchVolunteerList.module.scss';

export default function SearchVolunteerList({ items }) {
  console.log('list', items);

  // useQuery
  const { data, isLoading } = useQuery({
    queryKey: ['getShelterDetail'],
    queryFn: () => getShelterDetail(items[0].shelterId),
  });
  console.log(data);
  if (isLoading) return;

  return (
    <div className={styles.container}>
      <div className={styles['container-inner']}>
        <div className={styles.image}>
          <img src={data.imageList[0].filePath} />
        </div>
        <div className={styles.info}>
          <div className={styles.name}>{data.name}</div>
          <hr />
          <div className={styles.address}>{data.address}</div>
          <div className={styles.phoneNumber}>{data.phoneNumber}</div>
          <div className={styles.infoContent}>{data.infoContent}</div>
        </div>
      </div>
      <hr />
      <div style={{ height: '150px', overflowY: 'scroll' }}>
        {items.map((item, index) => (
          <SearchVolunteerItem key={index} item={item} />
        ))}
      </div>
    </div>
  );
}
