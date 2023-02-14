import React, { useState } from 'react';
import styles from './ImageSelect.module.scss';

export default function ImageSelect({ images }) {
  const [mainImage, setMainImage] = useState(images[0].filePath);
  const subImages = [];
  for (let index = 0; index < images.length; index++) {
    subImages.push(images[index].filePath);
  }
  const [click, setClick] = useState([true, false, false]);

  const handleSubImage = idx => {
    setMainImage(subImages[idx]);
  };

  return (
    <>
      <div className={styles['main-image']}>
        <img className={styles['main-image']} src={mainImage} alt="" />
      </div>
      <div className={styles['sub-image']}>
        <div>
          <img
            onClick={() => {
              handleSubImage(0);
              setClick([true, false, false]);
            }}
            className={click[0] ? styles['sub-image1'] : styles['non-click']}
            src={subImages[0]}
            alt=""
          />
        </div>
        <div>
          {subImages.length >= 2 ? (
            <img
              onClick={() => {
                handleSubImage(1);
                setClick([false, true, false]);
              }}
              className={click[1] ? styles['sub-image2'] : styles['non-click']}
              src={subImages[1]}
              alt=""
            />
          ) : (
            <div className={styles['empty-image']} />
          )}
        </div>
        <div>
          {subImages.length >= 3 ? (
            <img
              onClick={() => {
                handleSubImage(2);
                setClick([false, false, true]);
              }}
              className={click[2] ? styles['sub-image3'] : styles['non-click']}
              src={subImages[2]}
              alt=""
            />
          ) : (
            <div className={styles['empty-image']} />
          )}
        </div>
      </div>
    </>
  );
}
