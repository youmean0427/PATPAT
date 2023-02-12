import React, { useState } from 'react';
import ConsultingList from 'components/ShelterPage/Lists/ConsultingList';
import styles from './ShelterConsulting.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
export default function ShelterConsulting() {
  const myShelterId = useRecoilValue(myShelterIdState);
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
      <div className={styles.list}>
        <ConsultingList stateCode={click} shelterId={myShelterId} />
      </div>
    </ShelterContainer>
  );
}
