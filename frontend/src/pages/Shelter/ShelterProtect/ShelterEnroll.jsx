import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import { useState } from 'react';
import styles from './ShelterEnroll.module.scss';
import ExcelForm from 'components/ShelterPage/ShelterProtect/ExcelForm';
import EnrollForm from 'components/ShelterPage/ShelterProtect/EnrollForm';
export default function ShelterEnroll() {
  const [type, setType] = useState(0);
  return (
    <ShelterContainer title="보호 동물 등록">
      <div className={styles['type-box']}>
        <button
          onClick={() => setType(0)}
          className={type === 0 ? `${styles['type-box-item']} ${styles.selected}` : styles['type-box-item']}
        >
          EXCEL로 추가
        </button>
        <button
          onClick={() => setType(1)}
          className={type === 1 ? `${styles['type-box-item']} ${styles.selected}` : styles['type-box-item']}
        >
          직접 입력
        </button>
      </div>

      {type === 0 ? <ExcelForm /> : <EnrollForm />}
    </ShelterContainer>
  );
}
