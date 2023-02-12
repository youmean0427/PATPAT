import React, { useState } from 'react';
import styles from './Contents.module.scss';
import BoardsList from './Lists/BoardsList';
import MissingDogList from './Lists/MissingDogList';
import FavoriteList from './Lists/FavoriteList';
import VolunteerList from './Lists/VolunteerList';
import ConsultingList from './Lists/ConsultingList';

export default function Contents({ data }) {
  const [click, setClick] = useState([true, false, false, false, false]);

  return (
    <div className={styles.navbar}>
      <div className={styles.navbar__menu}>
        <button
          className={click[0] ? `${styles.button} ${styles.active}` : styles.button}
          onClick={() => setClick([true, false, false, false, false])}
        >
          내 가족 찾기
        </button>
        <button
          className={click[1] ? `${styles.button} ${styles.active}` : styles.button}
          onClick={() => setClick([false, true, false, false, false])}
        >
          꾹 리스트
        </button>
        <button
          className={click[2] ? `${styles.button} ${styles.active}` : styles.button}
          onClick={() => setClick([false, false, true, false, false])}
        >
          게시판 관리
        </button>
        <button
          className={click[3] ? `${styles.button} ${styles.active}` : styles.button}
          onClick={() => setClick([false, false, false, true, false])}
        >
          봉사신청 관리
        </button>
        <button
          className={click[4] ? `${styles.button} ${styles.active}` : styles.button}
          onClick={() => setClick([false, false, false, false, true])}
        >
          상담신청 관리
        </button>
      </div>
      <div className={styles.contents}>
        {click[0] ? <MissingDogList userId={data.userId} /> : null}
        {click[1] ? <FavoriteList /> : null}
        {click[2] ? <BoardsList /> : null}
        {click[3] ? <VolunteerList userId={data.userId} /> : null}
        {click[4] ? <ConsultingList userId={data.userId} /> : null}
      </div>
    </div>
  );
}
