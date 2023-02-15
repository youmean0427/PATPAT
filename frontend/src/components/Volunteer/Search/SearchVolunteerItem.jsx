import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React, { useState } from 'react';
import styles from './SearchVolunteerItem.module.scss';
import { useNavigate } from 'react-router-dom';

export default function SearchVolunteerItem({ item, itemToList }) {
  // useQuery
  const [itemData, setItemData] = useState(item);
  return (
    <div className={styles.mainContainer}>
      <div
        className={styles.container}
        onClick={() => {
          itemToList(itemData);
        }}
      >
        <div className={styles.title}>{item.title}</div>
        <div className={styles['container-dateState']}>
          <div className={styles.volunteerDate}>봉사날짜 : {item.volunteerDate}</div>
          <div className={styles.state}>{item.state}</div>
        </div>
      </div>
    </div>
  );
}
