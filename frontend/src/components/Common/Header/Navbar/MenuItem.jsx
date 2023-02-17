import { logout } from 'apis/utils/auth';
import React from 'react';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { isMobileMenuOpenState } from 'recoil/atoms/header';
import { isLoginState } from 'recoil/atoms/user';
import styles from './MenuItem.module.scss';
export default function MenuItem({ full, move, value, dropdown, handleClickModalOpen }) {
  const setIsOpen = useSetRecoilState(isMobileMenuOpenState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const handleClickMenuItem = () => {
    setIsOpen(false);
    if (value === '로그아웃') {
      logout();
      setIsLogin(false);
      toast('로그아웃 완료', { type: 'success' });
    } else if (value === '보호소 등록' && handleClickModalOpen) {
      handleClickModalOpen();
    }
  };
  return (
    <li
      onClick={() => handleClickMenuItem()}
      className={full ? `${styles['menu-item']} ${styles.full}` : styles['menu-item']}
    >
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
