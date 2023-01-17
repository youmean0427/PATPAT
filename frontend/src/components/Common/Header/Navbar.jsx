import React from 'react';
import { menuItems } from 'data/menuItems';
import { Link } from 'react-router-dom';
import styles from './Navbar.module.scss';
export default function Navbar() {
  return (
    <nav>
      <ul className={styles.menus}>
        {menuItems.map((menu, index) => {
          return (
            <li className={styles['menu-item']} key={index}>
              <Link to={menu.url}>{menu.title}</Link>
            </li>
          );
        })}
      </ul>
    </nav>
  );
}
