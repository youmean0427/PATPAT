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
export default function MenuList() {
  const [isLogin, setIsLogin] = useAuth();
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
        <MenuItem move="shelter/search" value="보호소" dropdown={shelter} />
        <MenuItem move="report" value="신고" />
        <MenuItem move="volunteer" value="봉사" />
        <MenuItem move="community" value="커뮤니티" />
        {!isLogin ? (
          <MenuItem move="login" value="로그인" />
        ) : isHaveShelter() ? (
          <MyProfileMenuItem dropdown={shelterUser} />
        ) : (
          <MyProfileMenuItem dropdown={normalUser} />
        )}
      </ul>
    </>
  );
}
const intro = [
  { title: 'PATPAT은', path: 'intro' },
  { title: '미션 & 비전', path: 'vision' },
  { title: '통계', path: 'statistics' },
];

const shelter = [
  { title: '보호소 찾기', path: 'shelter/search' },
  { title: '보호소 등록', path: 'shelter/enroll' },
];

const shelterUser = [
  { title: '마이페이지', path: 'mypage' },
  { title: '나의 보호소', path: `shelter/${JSON.parse(localStorage.getItem('user')).shelterId}/intro` },
  { title: '로그아웃', path: '/' },
];

const normalUser = [
  { title: '마이페이지', path: 'mypage' },
  { title: '보호소 등록', path: 'shelter/enroll' },
  { title: '로그아웃', path: '/' },
];
