import React, { useCallback, useEffect } from 'react';
import styles from './Header.module.scss';
import Container from 'containers/Container';
import Navbar from './Navbar/Navbar';
import { FaDog } from 'react-icons/fa';
import { useState } from 'react';
export default function Header() {
  const [isOpen, setIsOpen] = useState(false);
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
        <div className={styles.container}>
          <div className={styles['container-inner']}>
            <Navbar />
          </div>
        </div>
      </nav>
    </header>
  );
}
