import React from 'react';
import { NavLink } from 'react-router-dom';
import styles from './SubMenuList.module.scss';
export default function SubMenuList({ submenu }) {
  return (
    <ul className={styles.submenu}>
      {submenu.map((menu, index) => (
        <li key={index} className={styles['submenu-item']}>
          <NavLink>{menu}</NavLink>
        </li>
      ))}
    </ul>
  );
}
