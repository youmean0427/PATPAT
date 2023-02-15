import Banner from 'components/Common/Banner/Banner';
import React from 'react';
import styles from './Community.module.scss';
import { Outlet } from 'react-router';
import Navbar from 'components/Community/Navbar/Navbar';
import MenuLink from 'components/Community/Navbar/MenuLink';

export default function Commynity() {
  return (
    <div className={styles.container}>
      <Banner title="커뮤니티" />
      <Navbar>
        <MenuLink move="adoption" value="입양 후기" />
        <MenuLink move="share" value="무료 나눔" />
        <MenuLink move="info" value="정보 공유" />
      </Navbar>
      <Outlet />
    </div>
  );
}
