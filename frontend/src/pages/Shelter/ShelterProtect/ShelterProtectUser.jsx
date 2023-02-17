import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import styles from './ShelterProtectUser.module.scss';
import AbandonedDogItem from 'components/Common/Home/AbandonedDogItem';
import Select from 'react-select';
import { changeReportBreedList } from 'utils/changeSelectTemplate';
import { useCallback } from 'react';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { selectShelterBreedState } from 'recoil/atoms/shelter';

export default function ShelterProtectUser({ handleClickNext, handleClickPrev, data, page, setPage, breedData }) {
  const [breed, setBreed] = useRecoilState(selectShelterBreedState);
  const handleChangeOnBreedList = useCallback(selected => {
    setBreed({ breedId: selected.value, name: selected.label });
    setPage(1);
  });

  return (
    <ShelterContainer title="보호 동물">
      <span className={styles.total}>현재 {data.totalCount}개의 보호동물이 등록 되어있습니다.</span>
      <Select
        onChange={handleChangeOnBreedList}
        className="basic-single"
        options={changeReportBreedList(breedData)}
        placeholder="선택해주세요"
        value={breed.breedId ? { value: breed.breedId, label: breed.name } : null}
      />
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
            !data || data.totalPage === 0 || page === data.totalPage
              ? `${styles.button} ${styles.disabled}`
              : styles.button
          }
          disabled={!data || data.totalPage === 0 || page === data.totalPage ? true : false}
        >
          <MdArrowForwardIos />
        </button>
      </div>
      <div className={styles.list}>
        {data?.list?.map(item => {
          return <AbandonedDogItem key={item.protectId} item={item} />;
        })}
      </div>
    </ShelterContainer>
  );
}
