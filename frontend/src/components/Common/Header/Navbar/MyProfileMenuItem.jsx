import { logout } from 'apis/utils/auth';
import React from 'react';
import { Link } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { isLoginState } from 'recoil/atoms/user';
import styles from './MyProfileMenuItem.module.scss';
export default function MyProfileMenuItem({ dropdown }) {
  const { profileImageUrl, userName } = JSON.parse(localStorage.getItem('user'));
  const setIsLogin = useSetRecoilState(isLoginState);
  return (
    <li className={styles['profile']}>
      <span className={styles['profile-name']}>{userName}님</span>
      <img className={styles['profile-img']} src={profileImageUrl} alt="profile" />

      {dropdown && (
        <ul className={styles.submenu}>
          {dropdown.map((item, index) => {
            return (
              <li
                onClick={() => {
                  if (item.path === '/') {
                    logout();
                    setIsLogin(false);
                  }
                }}
                key={index}
                className={styles.subitem}
              >
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
