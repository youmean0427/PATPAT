import styles from './VolunteerDetail.module.scss';
import React from 'react';
export default function VolunteerDetail({ items }) {
  console.log('item', items);
  return (
    <div className={styles.container}>
      <div>봉사날짜 : {items.volunteerDate}</div>
      <div>{items.state}</div>
      <div>{items.title}</div>
      <button>신청</button>
    </div>
  );
}
