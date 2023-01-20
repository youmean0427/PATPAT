import React from 'react';
import { NavLink } from 'react-router-dom';
import { utilGetSideMenuIcon } from 'utils';
import styles from './SideMenuItem.module.scss';

export default function SideMenuItem({ title, url, icon }) {
  return (
    <NavLink className={styles.link} to={url}>
      {utilGetSideMenuIcon(icon)}
      <span>{title}</span>
    </NavLink>
  );
}
