import React, { useCallback, useEffect } from 'react';
import styles from './Header.module.scss';
import Container from 'containers/Container';
import Navbar from './Navbar/Navbar';
export default function Header() {
  const handleScroll = useCallback(() => {
    const header = document.querySelector(`.${styles.header}`);
    if (window.scrollY === 0) {
      header.classList.remove(styles.scroll);
    } else {
      header.classList.add(styles.scroll);
    }
  }, []);

  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);
  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <Container>
          <div className={styles['container-inner']}>
            <Navbar />
          </div>
        </Container>
        <div id={styles['mobile-menu']}>{/* 모바일 크기가 되었을 때 햄버거 클릭시 나올 메뉴 */}</div>
      </nav>
    </header>
  );
}
