import React, { useState } from 'react';
import ConsultingList from 'components/ShelterPage/Lists/ConsultingList';
import Modal from 'components/Common/Modal';
import styles from './ShelterConsulting.module.scss';
import { useLocation } from 'react-router-dom';
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
    <div className={styles.container}>
      <Modal open={modal} close={closeModal} title="상담시간 관리">
        <div className={styles['modal-content']}>
          <p className={styles['modal-title']}>상담시간 관리</p>
          <hr className={styles['modal-line']} />
          <div className={styles['modal-section']}>
            <p className={styles['modal-time']}>오전</p>
            <hr className={styles['modal-line2']} />
            <div className={`${styles['modal-select']} ${styles.morning}`}>
              <input className={styles['check-box']} type="checkbox" id="state0" />
              <label className={styles['modal-time']} htmlFor="state0">
                10:00 ~ 11:00
              </label>
            </div>
            <p className={styles['modal-time']}>오후</p>
            <hr className={styles['modal-line2']} />
            <div className={styles['modal-select']}>
              <input className={styles['check-box']} type="checkbox" id="state1" />
              <label className={styles['modal-time']} htmlFor="state1">
                14:00 ~ 15:00
              </label>
            </div>
            <div className={styles['modal-select']}>
              <input className={styles['check-box']} type="checkbox" id="state2" />
              <label className={styles['modal-time']} htmlFor="state2">
                15:00 ~ 16:00
              </label>
            </div>
            <div className={styles['modal-select']}>
              <input className={styles['check-box']} type="checkbox" id="state3" />
              <label className={styles['modal-time']} htmlFor="state3">
                16:00 ~ 17:00
              </label>
            </div>
            <div className={styles['modal-select']}>
              <input className={styles['check-box']} type="checkbox" id="state4" />
              <label className={styles['modal-time']} htmlFor="state4">
                17:00 ~ 18:00
              </label>
            </div>
          </div>
        </div>
      </Modal>
      <div className={styles.contents}>{click[3] ? <ConsultingList shelterId={location.state.shelterId} /> : null}</div>
    </div>
  );
}
