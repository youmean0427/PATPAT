import React from 'react';
import Slider from 'react-slick';
import bannerImg from 'assets/images/home-banner.png';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './SlideBanner.module.scss';
export default function SlideBanner() {
  const settings = {
    dots: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };
  return (
    <div>
      <Slider {...settings}>
        <div>
          <img className={styles['slide-img']} src={bannerImg} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg} alt="banner" />
        </div>
      </Slider>
    </div>
  );
}
