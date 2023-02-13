import { useQuery } from '@tanstack/react-query';
import { getVolReservationOfUser } from 'apis/api/volunteer';
import React, { useState } from 'react';
import VolunteerItem from 'components/MyPage/Items/VolunteerItem';
import styles from './MyVolunteer.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import NoData from 'components/Common/NoData';

export default function MyVolunteer() {
  const userId = JSON.parse(localStorage.getItem('user')).userId;
  const [page, setPage] = useState(1);
  const LIMIT = 8;

  const { data, isLoading } = useQuery(['getVolReservationOfUser', page], () => {
    return getVolReservationOfUser(LIMIT, page - 1, userId);
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };

  if (isLoading) return;

  return (
    <ShelterContainer title="봉사 관리">
      {data.totalCount === 0 ? (
        <NoData>신청된 봉사 내역이 없습니다.</NoData>
      ) : (
        <>
          <div className={styles.pagination}>
            <button
              onClick={handleClickPrev}
              className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
              disabled={page === 1 ? true : false}
            >
              <MdArrowBackIosNew />
            </button>
            <span>{page}</span>
            <button
              onClick={handleClickNext}
              className={
                data.totalPage === 0 || page === data.totalPage ? `${styles.button} ${styles.disabled}` : styles.button
              }
              disabled={data.totalPage === 0 || page === data.totalPage ? true : false}
            >
              <MdArrowForwardIos />
            </button>
          </div>
          <div className={styles.list}>
            {data.totalCount === 0 ? (
              <div>봉사 신청 내역이 없습니다.</div>
            ) : (
              data.list.map(item => {
                return <VolunteerItem key={item.reservationId} item={item} />;
              })
            )}
          </div>
        </>
      )}
    </ShelterContainer>
  );
}
