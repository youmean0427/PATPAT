import { useQuery } from '@tanstack/react-query';
import { getShelterProtectList } from 'apis/api/protect';
import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import styles from './ShelterProtectManager.module.scss';
export default function ShelterProtectManager({ shelterId }) {
  const { data, isLoading } = useQuery(['getShelterProtectList'], () => getShelterProtectList(shelterId));
  if (isLoading) return;
  return (
    <ShelterContainer title="동물 관리">
      <div className={styles.container}>
        <ul className={styles['protect-list']}>
          {data.map(item => {
            return (
              <li key={item.protectId} className={styles.protectItem}>
                {item.protectName}
              </li>
            );
          })}
        </ul>
        <div className={styles.manageForm}></div>
      </div>
    </ShelterContainer>
  );
}
