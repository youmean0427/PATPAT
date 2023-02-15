import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React, { useState } from 'react';
import { useEffect } from 'react';
import SearchVolunteerItem from './SearchVolunteerItem';
import styles from './SearchVolunteerList.module.scss';

export default function SearchVolunteerList({ items, listToMark }) {
  const [itemData, setItemData] = useState();
  const itemToList = x => {
    // setItemData(x);
    listToMark(x);
  };
  // useQuery

  const { data, isLoading } = useQuery({
    queryKey: ['getShelterDetail'],
    queryFn: () => getShelterDetail(items[0].shelterId),
  });
  if (isLoading) return;

  return (
    <div className={styles.container}>
      <div className={styles['container-inner']}>
        <div className={styles.image}>{data.imageList ? <img src={data.imageList[0].filePath} /> : null}</div>
        <div className={styles.info}>
          <div className={styles.name}>{data.name}</div>
          <hr />
          <div>
            <div className={styles.address}>{data.address}</div>
            <div className={styles.phoneNumber}>{data.phoneNumber}</div>
            <div className={styles.infoContent}>{data.infoContent}</div>
          </div>
        </div>
      </div>
      <hr />
      <div style={{ height: '180px', overflowY: 'scroll' }}>
        {items.map((item, index) => (
          <SearchVolunteerItem key={index} item={item} itemToList={itemToList} className={styles.volunteerItem} />
        ))}
      </div>
    </div>
  );
}
