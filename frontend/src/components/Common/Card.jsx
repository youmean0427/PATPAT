import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Card.module.scss';
export default function Card({ children }) {
  return <div className={styles.card}>{children}</div>;
}
