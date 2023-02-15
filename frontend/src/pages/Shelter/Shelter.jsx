import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { Outlet, useParams } from 'react-router';
import defaultShelterImage from 'assets/images/default-shelter.png';
import styles from './Shelter.module.scss';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { checkMyShelter } from 'utils/checkMyShelter';

export default function Shelter() {
  const params = useParams();
  const shelterId = params.shelterId;
  const myShelterId = useRecoilValue(myShelterIdState);
  const { data, isLoading } = useQuery({
    queryKey: ['getShelterDetailInfo', shelterId],
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
      <Navbar>
        <MenuLink move="intro" value="정보 보기" shelterId={shelterId} />
        <MenuLink move="protect" value="보호 동물" shelterId={shelterId} />
        <MenuLink
          move="volunteer"
          value={checkMyShelter(shelterId, myShelterId) ? '봉사 관리' : '봉사 신청'}
          shelterId={shelterId}
        />
        {checkMyShelter(shelterId, myShelterId) && (
          <MenuLink move="consulting" value="상담 관리" shelterId={shelterId} shelterName={data.name} />
        )}
      </Navbar>
      <Outlet />
    </div>
  );
}
