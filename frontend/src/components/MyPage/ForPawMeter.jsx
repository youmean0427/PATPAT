import { useQuery } from '@tanstack/react-query';
import { modifyUserInfo } from 'apis/api/user';
import React, { useState, useEffect, useRef } from 'react';
import styles from './ForPawMeter.module.scss';
import ForPawDog from 'assets/images/ForPawDog.gif';
import ModifyBtn from 'assets/images/modify-profile.png';
import ForPawInfo from 'assets/images/forpaw-info.png';
import Modal from '../Common/Modal';

export default function ForPawMeter({ data }) {
  const [click, setClick] = useState(false);
  const [modal, setModal] = useState(false);
  const [file, setFile] = useState();
  const [profile, setProfile] = useState('');
  const [preProfile, setPreProfile] = useState('');
  const [userName, setUserName] = useState('');

  const distance = 0;
  const fileInput = useRef(null);
  const reader = new FileReader();

  useEffect(() => {
    setProfile(data.profileImageUrl);
    setPreProfile(data.profileImageUrl);
    setUserName(data.username);
  }, []);

  const openModal = () => {
    setModal(true);
  };
  const closeModal = () => {
    setModal(false);
  };

  const saveModal = () => {
    alert('저장하시겠습니까?');
    setProfile(preProfile);
    createFormData();
    closeModal();
  };

  const createFormData = () => {
    const formData = new FormData();
    formData.append('userId', data.userId);
    formData.append('username', userName);
    formData.append('profileFile', file);
    modifyUserInfo(formData);
  };

  const handleUserName = e => {
    setUserName(e.target.value);
  };

  const handleUserProfile = e => {
    if (e.target.files[0]) {
      setPreProfile(e.target.files[0]);
      setFile(e.target.files[0]);
    } else return;

    reader.onload = () => {
      if (reader.readyState === 2) {
        setPreProfile(reader.result);
      }
    };
    reader.readAsDataURL(e.target.files[0]);
  };

  return (
    <div className={styles.ForPaw}>
      <div className={styles.container}>
        {click ? (
          <div className={styles['content-text']}>
            For Paw Meter는 봉사시간, 후기, 정보공유 등을 종합해서 만든 관심 지표입니다.
          </div>
        ) : null}
        <div className={styles.content}>
          <p>ForPawMeter</p>
          <img
            title="포포미터 정보"
            src={ForPawInfo}
            alt=""
            className={styles['forpaw-info']}
            onClick={() => setClick(cur => !cur)}
          />
        </div>
        <br />
        <img src={ForPawDog} alt="" className={styles['dog-gif']} style={{ marginLeft: `${distance}` }} />
        <img src={profile} alt="" className={styles['user-profile']} />
        <img title="프로필 수정" src={ModifyBtn} alt="" className={styles['modify-profile']} onClick={openModal} />
        <Modal open={modal} close={closeModal} save={saveModal} title="프로필 수정">
          <div className={styles['modal-content']}>
            <div className={styles['modal-profile']}>
              <img src={preProfile} alt="" onClick={() => fileInput.current.click()} title="프로필 이미지 수정" />
              <input
                type="file"
                style={{ display: 'none' }}
                accept="image/*"
                onChange={e => handleUserProfile(e)}
                ref={fileInput}
              />
            </div>
            <div className={styles['modal-name']}>
              <input type="text" value={userName} onChange={e => handleUserName(e)} />
            </div>
          </div>
        </Modal>
        <br />
        <div className={styles.road}></div>
      </div>
    </div>
  );
}
