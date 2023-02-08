import { logout } from 'apis/utils/auth';
import React from 'react';
import { Link } from 'react-router-dom';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { isMobileMenuOpenState } from 'recoil/atoms/header';
import { isLoginState } from 'recoil/atoms/user';
import styles from './MenuItem.module.scss';
export default function MenuItem({ move, value, dropdown }) {
  const setIsOpen = useSetRecoilState(isMobileMenuOpenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const handleClickMenuItem = () => {
    setIsOpen(false);
    if (value === '로그아웃') {
      logout();
      setIsLogin(false);
    }
  };
  return (
    <li onClick={() => handleClickMenuItem()} className={styles['menu-item']}>
      <Link to={move} className={styles['nav-link']}>
        {value}
      </Link>
      {dropdown && (
        <ul className={styles.submenu}>
          {dropdown.map((item, index) => {
            return (
              <li key={index} className={styles.subitem}>
                <Link className={styles.sublink} to={item.path}>
                  {item.title}
                </Link>
              </li>
            );
          })}
        </ul>
      )}
    </li>
  );
}
