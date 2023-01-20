import * as React from 'react';
import styles from './Navbar.module.scss';
import { menuItems } from 'data/menuItems';
import Dropdown from '../Dropdown/Dropdown';
export default function Navbar() {
  return (
    <nav className={styles['nav-wrap']}>
      {menuItems.map((menu, index) => (
        <Dropdown
          key={index}
          title={menu.title}
          submenu={menu.submenus}
          url={menu.url}
        />
      ))}
    </nav>
  );
}
