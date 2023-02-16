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
      <div>
        <div className={styles['protect-list']}>
          {data.map(item => {
            <span>{item.protectId}</span>;
          })}
        </div>
      </div>
    </ShelterContainer>
  );
}
