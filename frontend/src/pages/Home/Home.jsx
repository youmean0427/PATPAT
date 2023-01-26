import ReviewList from 'components/Home/ReviewList';
import SlideBanner from 'components/Home/SlideBanner';
import React from 'react';
import styles from './Home.module.scss';
export default function Home() {
  return (
    <div className={styles.wrap}>
      <SlideBanner />
      <ReviewList />
    </div>
  );
}
