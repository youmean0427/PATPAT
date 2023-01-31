import React from 'react';
import { Link } from 'react-router-dom';
import styles from './MbtiContainer.module.scss';
export default function MbtiContainer({ children }) {
  return (
    <div className={styles.container}>
      <div className={styles.inner}>
        <header className={styles.header}>
          <Link to="/mbti">PetBTI OF PATPAT</Link>
        </header>
        <div className={styles.main}>{children}</div>
      </div>
      <footer className={styles.footer}>나의 MBTI에 맞는 강아지는 ?</footer>
    </div>
  );
}
