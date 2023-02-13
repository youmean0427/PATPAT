import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React, { useState } from 'react';
import styles from './SearchVolunteerItem.module.scss';
import { useNavigate } from 'react-router-dom';

export default function SearchVolunteerItem({ item }) {
  const navigate = useNavigate();
  // useQuery

  return (
    <div>
      <div className={styles.container}>
        <div className={styles['container-dateState']}>
          <div className={styles.volunteerDate}>{item.volunteerDate}</div>
          <div className={styles.state}>{item.state}</div>
        </div>
        <div className={styles.title}>{item.title}</div>
      </div>
    </div>
  );
}
