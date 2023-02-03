import React from 'react';
import styles from './SelectMap.module.scss';
export default function SelectMapItem(props) {
  return (
    <path
      onMouseEnter={e => {
        const infoBox = document.querySelector(`.${styles['info-box']}`);
        infoBox.style.display = 'block';
        infoBox.textContent = `${props.name}`;
      }}
      onMouseLeave={() => {
        const infoBox = document.querySelector(`.${styles['info-box']}`);
        infoBox.style.display = 'none';
      }}
      onMouseMove={e => {
        const infoBox = document.querySelector(`.${styles['info-box']}`);

        infoBox.style.top = `${e.clientY - 60}px`;
        infoBox.style.left = `${e.clientX}px`;
      }}
      {...props}
    ></path>
  );
}
