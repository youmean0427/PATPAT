import React from 'react';
import { useLocation } from 'react-router-dom';
import styles from './MissingDogDetail.module.scss';
import DogDetailContent from 'components/Report/Detail/DogDetailContent';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
export default function MissingDogDetail() {
  const location = useLocation();
  const item = location.state.missingId;
  const state = 0;
  return (
    <div>
      <DogDetailContent item={item} state={state} />
      <hr />
      <div className={styles['container-button']}>
        <Navbar>
          <MenuLink move="/report/" value="목록" />
        </Navbar>
      </div>
    </div>
  );
}
