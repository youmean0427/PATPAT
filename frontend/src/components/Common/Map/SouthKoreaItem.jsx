import React from 'react';
import styles from './SouthKorea.module.scss';
export default function SouthKoreaItem(props) {
  const { title, count } = props;
  return (
    <path
      onMouseEnter={e => {
        const infoBox = document.querySelector(`.${styles.info}`);
        infoBox.style.display = 'block';
        infoBox.textContent = `${title} : ${count}개`;
      }}
      onMouseLeave={() => {
        const infoBox = document.querySelector(`.${styles.info}`);
        infoBox.style.display = 'none';
      }}
      onMouseMove={e => {
        const infoBox = document.querySelector(`.${styles.info}`);

        infoBox.style.top = `${e.clientY - 60}px`;
        infoBox.style.left = `${e.clientX}px`;
      }}
      {...props}
    ></path>
  );
}
