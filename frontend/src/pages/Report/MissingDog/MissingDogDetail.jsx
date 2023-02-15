import React from 'react';
import { useParams } from 'react-router-dom';
import styles from './MissingDogDetail.module.scss';

import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import MissingDogDetailContent from 'components/Report/Detail/MissingDogDetailContent';
export default function MissingDogDetail() {
  const item = useParams();
  const state = 0;

  return (
    <div>
      <MissingDogDetailContent item={item.id} state={state} />
      <hr />
      <div className={styles['container-button']}>
        <Navbar>
          <MenuLink move="/report/" value="목록" />
        </Navbar>
      </div>
    </div>
  );
}
