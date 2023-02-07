import React from 'react';
import { useLocation } from 'react-router-dom';
import styles from './MyMissing.module.scss';

export default function MyMissing() {
  const location = useLocation();
  const item = JSON.parse(localStorage.getItem('user'));
  const missingId = location.state.missingId;
  const dogName = location.state.name;
  const thumbnail = location.thumbnail;
  const userId = item.userId;
  const userName = item.userName;

  const number = 13;

  return (
    <div className={styles.container}>
      <div className={styles['top-content']}>
        <div className={styles['dog-img']}>
          <img src="https://m.bowell.co.kr/web/product/medium/202205/2a42858699ebd5f98184699b0d1a4a3c.jpg" alt="" />
        </div>
        <div className={styles['comment']}>
          <p>
            <span>{userName}</span>님이 찾고 계신 가족 <span>{dogName}</span>(와)과 유사한 강아지가 총{' '}
            <span>{number}</span>건 검색되었습니다.
          </p>
          <br />
          <p>이미지 카드를 클릭하여 정보를 상세히 확인하고 보호소와의 상담을 신청할 수 있습니다.</p>
          <br />
          <p>
            반드시 <span>{dogName}</span>을(를) 찾기를 PATPAT이 기원합니다.
          </p>
        </div>
      </div>
      <hr className={styles.line} />
      <div className={styles['bottom-content']}>보호소 리스트</div>
    </div>
  );
}
