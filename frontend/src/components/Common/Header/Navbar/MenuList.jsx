import useAuth from 'hooks/useAuth';
import React from 'react';
import { useEffect } from 'react';
import MenuItem from './MenuItem';
import styles from './MenuList.module.scss';
import { FaDog } from 'react-icons/fa';
import { GiSittingDog } from 'react-icons/gi';
import { useRecoilState } from 'recoil';
import { isMobileMenuOpenState } from 'recoil/atoms/header';
import { isHaveShelter } from 'utils/checkMyShelter';
import MyProfileMenuItem from './MyProfileMenuItem';
import { myShelterIdState } from 'recoil/atoms/user';
import { useQuery } from '@tanstack/react-query';
import { getAuthShelterList } from 'apis/api/shelter';
import { getUserInfo } from 'apis/api/user';
import Alarm from '../Alarm';

export default function MenuList({ handleClickModalOpen }) {
  const [isLogin, setIsLogin] = useAuth();
  const [isOpen, setIsOpen] = useRecoilState(isMobileMenuOpenState);
  const [myShelterId, setMyShelterId] = useRecoilState(myShelterIdState);
  const handleClickMobileMenu = () => {
    setIsOpen(prev => !prev);
  };
  useEffect(() => {
    if (localStorage.getItem('isLogin')) {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
    if (localStorage.getItem('user')) {
      const shelterId = JSON.parse(localStorage.getItem('user')).shelterId;
      setMyShelterId(shelterId);
    } else {
      setMyShelterId(null);
    }
  }, [setIsLogin, setMyShelterId]);
  const intro = [
    { title: 'PATPAT은', path: 'intro' },
    { title: '미션 & 비전', path: 'vision' },
    { title: 'PETBTI', path: 'mbti' },
  ];

  return (
    <>
      <div onClick={handleClickMobileMenu} className={styles['mobile-menu']}>
        {isOpen ? (
          <GiSittingDog color="#a1887f" size="3rem" />
        ) : (
          <FaDog className={styles['fa-dog']} color="#a1887f" size="3rem" />
        )}
      </div>
      <ul
        className={
          !isOpen
            ? styles.menu
            : isLogin
            ? `${styles.menu} ${styles.active}`
            : `${styles.menu} ${styles.active} ${styles.logout}`
        }
      >
        <MenuItem full={false} move="intro" value="소개" dropdown={intro} />
        <MenuItem full={false} move="shelter/search" value="보호소 찾기" />
        <MenuItem full={false} move="report" value="실종 신고" />
        <MenuItem full={false} move="volunteer" value="봉사" />
        <MenuItem full={false} move="community/adoption" value="커뮤니티" />
        <div className={styles.mobile}>
          <MenuItem move="mypage/missing" value="마이페이지" />
          {isHaveShelter(myShelterId) ? (
            <MenuItem full={false} move={`shelter/${myShelterId}/intro`} value="나의 보호소" />
          ) : (
            <MenuItem full={false} move="" value="보호소 등록" handleClickModalOpen={handleClickModalOpen} />
          )}
          {isLogin ? (
            <MenuItem full={false} move="/" value="로그아웃" />
          ) : (
            <MenuItem full={false} move="login" value="로그인" />
          )}
        </div>

        {!isLogin ? (
          <MenuItem full={true} move="login" value="로그인" />
        ) : (
          <>
            <Alarm />
            <MyProfileMenuItem handleClickModalOpen={handleClickModalOpen} isHaveShelter={true} />
          </>
        )}
      </ul>
    </>
  );
}
