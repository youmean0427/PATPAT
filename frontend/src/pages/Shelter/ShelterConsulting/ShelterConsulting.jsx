import React from 'react';
import ConsultingList from 'components/ShelterPage/Lists/ConsultingList';
import styles from './ShelterConsulting.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
export default function ShelterConsulting() {
  const myShelterId = useRecoilValue(myShelterIdState);
  return (
    <ShelterContainer title="상담 관리">
      <div className={styles.list}>
        <ConsultingList shelterId={myShelterId} />
      </div>
    </ShelterContainer>
  );
}
