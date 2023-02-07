import useAuth from 'hooks/useAuth';
import React from 'react';
import { useEffect, useState } from 'react';
import MenuItem from './MenuItem';
import styles from './MenuList.module.scss';
import { FaDog } from 'react-icons/fa';
import { GiSittingDog } from 'react-icons/gi';
import { useRecoilState } from 'recoil';
import { isMobileMenuOpenState } from 'recoil/atoms/header';
import AuthButton from './AuthButton';

export default function MenuList() {
  const [isLogin, setIsLogin, handleClickLogout, handleClickLogin] = useAuth();
  const [isOpen, setIsOpen] = useRecoilState(isMobileMenuOpenState);
  const handleClickMobileMenu = () => {
    setIsOpen(prev => !prev);
  };
  useEffect(() => {
    if (localStorage.getItem('isLogin')) {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
  }, []);
  return (
    <>
      <div onClick={handleClickMobileMenu} className={styles['mobile-menu']}>
        {isOpen ? <GiSittingDog color="#a1887f" size="3rem" /> : <FaDog color="#a1887f" size="3rem" />}
      </div>
      <ul className={isOpen ? `${styles.menu} ${styles.active}` : styles.menu}>
        <MenuItem move="intro" value="소개" dropdown={intro} />
        <MenuItem move="shelter/search" value="보호소" />
        <MenuItem move="report" value="신고" />
        <MenuItem move="volunteer" value="봉사" />
        <MenuItem move="community" value="커뮤니티" />
        <MenuItem move={isLogin ? '/' : 'login'} value={isLogin ? '로그아웃' : '로그인'} />
      </ul>
    </>
  );
}
const intro = [
  { title: 'PATPAT은', path: 'intro' },
  { title: '미션 & 비전', path: 'vision' },
  { title: '통계', path: 'statistics' },
];
