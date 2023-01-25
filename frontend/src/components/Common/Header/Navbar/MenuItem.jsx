import React from 'react';

import styles from './MenuItem.module.scss';
import SubMenuList from './SubMenuList';
export default function MenuItem({ value, submenu }) {
  return (
    <li className={styles['menu-item']}>
      <div>{value}</div>
      <SubMenuList submenu={submenu} />
    </li>
  );
}
