import React, { useState } from 'react';
import styles from './MyBoard.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import BoardList from 'components/MyPage/Lists/BoardList';

export default function MyBoard() {
  const [typecode, setTypeCode] = useState(0);

  return (
    <ShelterContainer title="게시판 관리">
      <div className={styles.filter}>
        <span
          onClick={() => setTypeCode(0)}
          className={typecode === 0 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          입양 후기
        </span>
        <span
          onClick={() => setTypeCode(2)}
          className={typecode === 2 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          무료 나눔
        </span>
        <span
          onClick={() => setTypeCode(1)}
          className={typecode === 1 ? `${styles['filter-item']} ${styles.active}` : styles['filter-item']}
        >
          정보 공유
        </span>
      </div>
      <div className={styles.list}>
        <BoardList typeCode={typecode} />
      </div>
    </ShelterContainer>
  );
}
