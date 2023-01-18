import React, { useEffect, useState } from 'react';
import styles from './Dropdown.module.scss';
// header 메뉴 클릭시 DropDownMenu가 있다면 보여줘야 함
export default function DropdownMenu({ submenu }) {
  return (
    <ul className={styles['sub-menu']}>
      {submenu.map((item, index) => (
        <li className={styles['sub-menu-item']} key={index}>
          {item.title}
        </li>
      ))}
    </ul>
  );
}
