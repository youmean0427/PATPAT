import React, { useState } from 'react';
import styles from './Contents.module.scss';

export default function Contents() {
  const [click, setClick] = useState([true, false, false, false, false]);
  return (
    <div className={styles.container}>
      <div className={styles.buttons}>
        <button
          className={click[0] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([true, false, false, false, false])}
        >
          내 가족 찾기
        </button>
        <button
          className={click[1] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, true, false, false, false])}
        >
          꾹 리스트
        </button>
        <button
          className={click[2] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, false, true, false, false])}
        >
          게시판 관리
        </button>
        <button
          className={click[3] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, false, false, true, false])}
        >
          봉사신청 관리
        </button>
        <button
          className={click[4] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, false, false, false, true])}
        >
          상담신청 관리
        </button>
      </div>
    </div>
  );
}
