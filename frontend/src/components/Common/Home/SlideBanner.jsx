import React from 'react';
import Slider from 'react-slick';
import bannerImg1 from 'assets/images/b1.png';
import bannerImg2 from 'assets/images/b2.png';
import bannerImg3 from 'assets/images/b3.png';
import bannerImg4 from 'assets/images/b4.png';
import bannerImg5 from 'assets/images/b5.png';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './SlideBanner.module.scss';
export default function SlideBanner() {
  const settings = {
    dots: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    speed: 800,
  };
  return (
    <div>
      <Slider {...settings}>
        <div>
          <img className={styles['slide-img']} src={bannerImg4} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg2} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg1} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg3} alt="banner" />
        </div>
        <div>
          <img className={styles['slide-img']} src={bannerImg5} alt="banner" />
        </div>
      </Slider>
    </div>
  );
}
