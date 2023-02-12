import React from 'react';
import { useLocation } from 'react-router-dom';
import styles from './PersonalDogDetail.module.scss';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import PersonalDogDetailContent from 'components/Report/Detail/PersonalDogDetailContent';
export default function PersonalDogDetail() {
  const location = useLocation();
  const item = location.state.personalProtectionId;
  const state = 1;
  return (
    <div>
      <div>
        <PersonalDogDetailContent item={item} state={state} />
        <hr />
        <div className={styles['container-button']}>
          <Navbar>
            <MenuLink move="/report/" value="목록" />
          </Navbar>
        </div>
      </div>
    </div>
  );
}
