import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';
import styles from './ShelterProtectUser.module.scss';
import AbandonedDogItem from 'components/Common/Home/AbandonedDogItem';

export default function ShelterProtectUser({ handleClickNext, handleClickPrev, data, page }) {
  return (
    <ShelterContainer title="보호 동물">
      <span>현재 {data.totalCount}개의 보호동물이 등록 되어있습니다.</span>
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
