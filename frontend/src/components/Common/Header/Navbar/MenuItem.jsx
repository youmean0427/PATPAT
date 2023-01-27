import React, { useState } from 'react';

import styles from './MenuItem.module.scss';
import SubMenuList from './SubMenuList';
export default function MenuItem({ value, submenu }) {
  const [open, setOpen] = useState(false);
  return (
    <li className={styles['menu-item']} onClick={() => setOpen(open => !open)}>
      <div>{value}</div>
      {open && <SubMenuList submenu={submenu} />}
    </li>
  );
}
