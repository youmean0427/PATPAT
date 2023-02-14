import React, { useState } from 'react';
import ConsultingList from 'components/MyPage/Lists/ConsultingList';
import styles from './MyConsulting.module.scss';
import ShelterContainer from 'containers/ShelterContainer';

export default function MyConsulting() {
  const [click, setClick] = useState(7);
  return (
    <ShelterContainer title="상담 관리">
      <div className={styles.filter}>
        <span
          onClick={() => setClick(7)}
          className={click === 7 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          전체
        </span>
        <span
          onClick={() => setClick(0)}
          className={click === 0 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          대기
        </span>
        <span
          onClick={() => setClick(1)}
          className={click === 1 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          승인
        </span>
      </div>
      <ConsultingList stateCode={click} />
    </ShelterContainer>
  );
}
