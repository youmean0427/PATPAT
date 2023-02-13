import { useQuery } from '@tanstack/react-query';
import { getUserInfo } from 'apis/api/user';
import { logout } from 'apis/utils/auth';
import useModal from 'hooks/useModal';
import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { isLoginState, myShelterIdState } from 'recoil/atoms/user';
import { isHaveShelter } from 'utils/checkMyShelter';
import styles from './MyProfileMenuItem.module.scss';
export default function MyProfileMenuItem({ handleClickModalOpen }) {
  const setIsLogin = useSetRecoilState(isLoginState);
  const setMyShelterId = useSetRecoilState(myShelterIdState);
  const location = useLocation();

  const { data, isLoading } = useQuery(['getUserInfo'], getUserInfo, {
    onSuccess: data => {
      setMyShelterId(data.shelterId);
    },
    onError: e => {
      console.error(e);
    },
  });
  if (isLoading) return;

  const shelterUser = [
    { title: '마이페이지', path: 'mypage' },
    { title: '나의 보호소', path: `shelter/${data.shelterId}/intro` },
    { title: '로그아웃', path: '/' },
  ];

  const normalUser = [
    { title: '마이페이지', path: 'mypage/missing' },
    { title: '보호소 등록', path: '' },
    { title: '로그아웃', path: '/' },
  ];
  return (
    <li className={styles.profile}>
      <div className={styles['profile-name']}>{data.username}님</div>
      <img className={styles['profile-img']} src={data.profileImageUrl} alt="profile" />

      {isHaveShelter(data.shelterId) ? (
        <ul className={styles.submenu}>
          {shelterUser.map((item, index) => {
            return (
              <li
                onClick={() => {
                  if (item.path === '/') {
                    logout();
                    setIsLogin(false);
                  } else if (item.path === '') {
                    handleClickModalOpen();
                  }
                }}
                key={index}
                className={styles.subitem}
              >
                <Link className={styles.sublink} to={item.path === '' ? location.pathname : item.path}>
                  {item.title}
                </Link>
              </li>
            );
          })}
        </ul>
      ) : (
        <ul className={styles.submenu}>
          {normalUser.map((item, index) => {
            return (
              <li
                onClick={() => {
                  if (item.path === '/') {
                    logout();
                    setIsLogin(false);
                  } else if (item.path === '') {
                    handleClickModalOpen();
                  }
                }}
                key={index}
                className={styles.subitem}
              >
                <Link className={styles.sublink} to={item.path === '' ? location.pathname : item.path}>
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
