import React from 'react';
import { Navigation, Pagination } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';
import styles from './ImageSlide.module.scss';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import './ImageSlide.css';

export default function ImageSlide({ imageList }) {
  return (
    <Swiper
      className={styles.slider}
      modules={[Navigation, Pagination]}
      spaceBetween={50}
      slidesPerView={1}
      navigation
      pagination={{ clickable: true }}
    >
      {imageList.map((item, index) => (
        <SwiperSlide key={index}>
          <img src={item.filePath} alt="이미지 보호소" />
        </SwiperSlide>
      ))}
    </Swiper>
  );
}
