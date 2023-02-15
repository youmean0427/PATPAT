import React from 'react';
import Container from 'containers/Container';
import styles from './Footer.module.scss';
export default function Footer() {
  return (
    <footer className={styles.footer}>
      <Container>
        <div className={styles.wrapper}>footer</div>
      </Container>
    </footer>
  );
}
