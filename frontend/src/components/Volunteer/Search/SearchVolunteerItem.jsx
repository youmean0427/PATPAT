import React, { useState } from 'react';
import styles from './SearchVolunteerItem.module.scss';

export default function SearchVolunteerItem({ item, itemToList }) {
  // useQuery
  const [itemData] = useState(item);
  return (
    <div className={styles.mainContainer}>
      <div
        className={styles.container}
        onClick={() => {
          itemToList(itemData);
        }}
      >
        <div className={styles.title}>{item.title}</div>
        <div className={styles['container-dateState']}>
          <div className={styles.volunteerDate}>봉사날짜 : {item.volunteerDate}</div>
          {item.stateCode === 0 ? <div className={styles.stateCode0}>신청 가능</div> : null}
          {item.stateCode === 1 ? <div className={styles.stateCode1}>신청 마감</div> : null}
          {item.stateCode === 2 ? <div className={styles.stateCode2}>{item.state}</div> : null}
          {item.stateCode === 3 ? <div className={styles.stateCode3}>{item.state}</div> : null}
        </div>
      </div>
    </div>
  );
}
