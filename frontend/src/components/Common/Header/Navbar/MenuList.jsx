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
    { title: '통계', path: 'statistics' },
    { title: 'PETBTI', path: 'mbti' },
  ];

  const shelter = [{ title: '보호소 찾기', path: 'shelter/search' }];

  return (
    <>
      <div onClick={handleClickMobileMenu} className={styles['mobile-menu']}>
        {isOpen ? (
          <GiSittingDog color="#a1887f" size="3rem" />
        ) : (
          <FaDog className={styles['fa-dog']} color="#a1887f" size="3rem" />
        )}
      </div>
      <ul className={isOpen ? `${styles.menu} ${styles.active}` : styles.menu}>
        <MenuItem move="intro" value="소개" dropdown={intro} />
        <MenuItem move="shelter/search" value="보호소" dropdown={shelter} />
        <MenuItem move="report" value="실종 신고" />
        <MenuItem move="volunteer" value="봉사" />
        <MenuItem move="community/adoption" value="커뮤니티" />

        {!isLogin ? (
          <MenuItem move="login" value="로그인" />
        ) : (
          <MyProfileMenuItem handleClickModalOpen={handleClickModalOpen} isHaveShelter={true} />
        )}
      </ul>
    </>
  );
}
