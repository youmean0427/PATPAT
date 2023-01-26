import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './loginNavigation.module.scss';
import Logo from 'assets/images/logo.png';
export default function LoginHeader() {
  const navigate = useNavigate();

  const moveToMain = () => {
    navigate('/home');
  };

  return (
    <div className={styles.navigation}>
      <img src={Logo} alt="" onClick={moveToMain} />
    </div>
  );
}
