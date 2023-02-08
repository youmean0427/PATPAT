import React from 'react';
import styles from './MenuLink.module.scss';
import { NavLink } from 'react-router-dom';

export default function MenuLink({ move, value, shelterId }) {
  return (
    <NavLink
      className={({ isActive }) => (isActive ? `${styles.button} ${styles.active}` : styles.button)}
      to={move}
      state={{ shelterId }}
    >
      {value}
    </NavLink>
  );
}
