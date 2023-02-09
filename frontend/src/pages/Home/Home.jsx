import SlideBanner from 'components/Home/SlideBanner';
import React from 'react';
import styles from './Home.module.scss';
import AbandonedDogList from 'components/Home/AbandonedDogList';
import Section from 'components/Home/Section';
import { Link } from 'react-router-dom';
import logo from 'assets/images/logo.png';
import ReviewList from 'components/Home/ReviewList';
import Loading from 'components/Common/Loading';
export default function Home() {
  return (
    <div className={styles.wrap}>
      <SlideBanner />
      {/* 유기견 리스트 */}
      <Section accent="가족" basic="을 기다리고 있어요">
        <AbandonedDogList />
        <div className={styles['link-box']}>
          <Link to="/abandoneds" className={styles.more}>
            더 보기
          </Link>
        </div>
      </Section>
      {/* PATPAT 슬로건 적은 banner */}
      <Section accent="유기견" basic="을 위한 플랫폼 PATPAT">
        <div className={styles.inner}>
          <div>
            <div className={styles.title}>PATPAT</div>
            <div className={styles.desc}>
              <div>
                <span>유기견</span>들에게 필요한 것은
              </div>
              <div>
                간식도 산책도 아닙니다 <span>가족</span> 입니다
              </div>
            </div>
          </div>
          <div>
            <img className={styles['logo-img']} src={logo} alt="logo" />
          </div>
          <div className={styles.triangle}></div>
        </div>
      </Section>
      {/* 입양 후기 리스트 */}
      <Section accent="가족" basic="을 찾은 아이들">
        <ReviewList />
        <div className={styles['link-box']}>
          <Link to="/reviews" className={styles.more}>
            더 보기
          </Link>
        </div>
      </Section>
    </div>
  );
}
