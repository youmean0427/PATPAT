import React from 'react';
import styles from './Images.module.scss';
const Images = ({ previewImages }) => {
  return (
    <>
      {previewImages.map(url => {
        return <img className={styles.img} alt={url} key={url} src={url} />;
      })}
    </>
  );
};

export default Images;
