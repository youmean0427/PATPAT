import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { Outlet, useParams } from 'react-router';
import defaultShelterImage from 'assets/images/default-shelter.png';
import styles from './Shelter.module.scss';
import { NavLink } from 'react-router-dom';

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
      <div className={styles.header}>
        <div className={styles.thumbnail}>
          {data.fileDto ? (
            <img src={data.fileDto.thumbnail} alt="보호소 대표 이미지" />
          ) : (
            <img src={defaultShelterImage} alt="보호소 대표 이미지" />
          )}
        </div>
        <div className={styles['title-box']}>
          <div className={styles.title}>{data.name}</div>
          <div className={styles['sub-title']}>
            <span>{data.name}</span>에 방문해주셔서 감사합니다
          </div>
        </div>
      </div>
      <div className={styles.navbar}>
        <div className={styles.navbar__menu}>
          <NavLink
            className={({ isActive }) => (isActive ? `${styles.button} ${styles.active}` : styles.button)}
            to="intro"
          >
            정보 보기
          </NavLink>
          <NavLink
            className={({ isActive }) => (isActive ? `${styles.button} ${styles.active}` : styles.button)}
            to="protect"
          >
            보호 동물
          </NavLink>
          <NavLink
            className={({ isActive }) => (isActive ? `${styles.button} ${styles.active}` : styles.button)}
            to="volunteer"
          >
            봉사 신청
          </NavLink>
          <NavLink
            className={({ isActive }) => (isActive ? `${styles.button} ${styles.active}` : styles.button)}
            to="consulting"
          >
            상담 신청
          </NavLink>
        </div>
      </div>
      <Outlet />
    </div>
  );
}
