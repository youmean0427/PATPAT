import React, { useState } from 'react';
import ConsultingList from 'components/ShelterPage/Lists/ConsultingList';
import Modal from 'components/Common/Modal';
import styles from './ShelterConsulting.module.scss';
import { useLocation } from 'react-router-dom';
import ShelterContainer from 'containers/ShelterContainer';
export default function ShelterConsulting() {
  const [click, setClick] = useState([false, false, false, true]);
  const [modal, setModal] = useState(false);
  const location = useLocation();
  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  return (
    <ShelterContainer title="상담 관리">
      <div className={styles.list}>
        <ConsultingList shelterId={location.state.shelterId} />
      </div>
    </ShelterContainer>
  );
}
