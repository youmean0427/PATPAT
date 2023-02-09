import { useQuery } from '@tanstack/react-query';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getProtectListOfShelter } from 'apis/api/protect';
import AbandonedDogItem from 'components/Home/AbandonedDogItem';
import styles from './ShelterProtect.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import { BsPlusCircleDotted } from 'react-icons/bs';
import useModal from 'hooks/useModal';
import AddProtectModal from 'components/Common/Modal/shelters/AddProtectModal';
export default function ShelterProtect() {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();

  const {
    state: { shelterId },
  } = useLocation();
  const [page, setPage] = useState(1);

  const LIMIT = 8;

  const { data, isLoading } = useQuery(['protectListOfShelter', shelterId, page], () => {
    return getProtectListOfShelter(shelterId, LIMIT, page - 1);
  });
  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };
  if (isLoading) return;
  return (
    <ShelterContainer title="보호 동물">
      <span>현재 {data.totalCount}개의 보호동물이 등록 되어있습니다.</span>
      <button onClick={handleClickModalOpen} className={styles.add}>
        <BsPlusCircleDotted />
      </button>
      <div className={styles.pagination}>
        <button
          onClick={handleClickPrev}
          className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
          disabled={page === 1 ? true : false}
        >
          <MdArrowBackIosNew />
        </button>
        <button
          onClick={handleClickNext}
          className={page === data.totalPage ? `${styles.button} ${styles.disabled}` : styles.button}
          disabled={page === data.totalPage ? true : false}
        >
          <MdArrowForwardIos />
        </button>
      </div>
      <div className={styles.list}>
        {data.list.map(item => {
          return <AbandonedDogItem key={item.protectId} item={item} />;
        })}
      </div>
      {isOpen && (
        <AddProtectModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} shelterId={shelterId} />
      )}
    </ShelterContainer>
  );
}
