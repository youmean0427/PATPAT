import React from 'react';
import { Link } from 'react-router-dom';
import styles from './SubMenuList.module.scss';
export default function SubMenuList({ submenu }) {
  return (
    <ul className={styles.submenu}>
      {submenu.map((menu, index) => (
        <li key={index} className={styles['submenu-item']}>
          <Link>{menu}</Link>
        </li>
      ))}
    </ul>
  );
}
