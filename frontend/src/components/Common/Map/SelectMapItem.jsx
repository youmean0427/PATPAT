import React from 'react';
import styles from './SelectMap.module.scss';
function SelectMapItem(props) {
  return (
    <path
      onMouseEnter={e => {
        const infoBox = document.querySelector(`.${styles.info}`);
        infoBox.style.display = 'block';
        infoBox.textContent = `${props.name}`;
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

export default React.memo(SelectMapItem);
