import React, { useState } from 'react';
import styles from './Dropdown.module.scss';
import DropdownMenu from './DropdownMenu';
export default function Dropdown({ submenu, title, url }) {
  const [open, setOpen] = useState(false);

  const handleClick = () => {
    setOpen(prev => !prev);
  };
  return (
    <div className={styles['nav-item-box']}>
      <div className={styles['nav-item']} onClick={handleClick}>
        {title}
      </div>
      {submenu && open && <DropdownMenu submenu={submenu} />}
    </div>
  );
}
