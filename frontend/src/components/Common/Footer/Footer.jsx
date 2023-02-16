import React from 'react';
import Container from 'containers/Container';
import styles from './Footer.module.scss';
import { Link } from 'react-router-dom';
export default function Footer() {
  return (
    <footer className={styles.footer}>
      <Container>
        <div className={styles.wrapper}>
          <Link to="/mbti">PETBTI 바로가기</Link>
        </div>
      </Container>
    </footer>
  );
}
