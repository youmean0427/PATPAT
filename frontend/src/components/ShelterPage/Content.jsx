import React, { useState } from 'react';
import styles from './Content.module.scss';
import ConsultingList from './Lists/ConsultingList';
import Modal from '../Common/Modal';

export default function Content() {
  const [click, setClick] = useState([false, false, false, true]);
  const [modal, setModal] = useState(false);

  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  return (
    <div className={styles.container}>
      <div className={styles.buttons}>
        <button
          className={click[0] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([true, false, false, false])}
        >
          정보
        </button>
        <button
          className={click[1] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, true, false, false])}
        >
          보호 동물
        </button>
        <button
          className={click[2] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, false, true, false])}
        >
          봉사 관리
        </button>
        <button
          className={click[3] ? styles['btn-click'] : styles.btn}
          onClick={() => setClick([false, false, false, true])}
        >
          상담 관리
        </button>
      </div>
      {click[2] || click[3] ? (
        <button onClick={openModal} className={styles.manage}>
          관리
        </button>
      ) : null}

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
      <div className={styles.contents}>{click[3] ? <ConsultingList shelterId="0" shelterName="보호소1" /> : null}</div>
    </div>
  );
}
