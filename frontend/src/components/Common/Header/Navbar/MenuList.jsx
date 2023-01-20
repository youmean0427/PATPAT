import React from 'react';
import { NavLink } from 'react-router-dom';
import MenuItem from './MenuItem';
import styles from './MenuList.module.scss';
export default function MenuList() {
  return (
    <div className={styles['menu-box']}>
      <ul className={styles.menu}>
        <MenuItem
          value="소개"
          submenu={['인사말', '미션 & 비전', '활동 내역', '통계']}
        />
        <li className={styles['menu-item']}>
          <div>소개</div>
          <ul className={styles.submenu}>
            <li className={styles['submenu-item']}>
              <NavLink>인사말</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>미션 & 비전</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>활동 내역</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>통계</NavLink>
            </li>
          </ul>
        </li>
        <li className={styles['menu-item']}>
          <div>보호소</div>
          <ul className={styles.submenu}>
            <li className={styles['submenu-item']}>
              <NavLink>보호소 찾기</NavLink>
            </li>
          </ul>
        </li>
        <li className={styles['menu-item']}>
          <div>신고</div>
          <ul className={styles.submenu}>
            <li className={styles['submenu-item']}>
              <NavLink>실종 신고</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>임시보호 신고</NavLink>
            </li>
          </ul>
        </li>
        <li className={styles['menu-item']}>
          <div>봉사</div>
          <ul className={styles.submenu}>
            <li className={styles['submenu-item']}>
              <NavLink>봉사 신청</NavLink>
            </li>
          </ul>
        </li>
        <li className={styles['menu-item']}>
          <div>커뮤니티</div>
          <ul className={styles.submenu}>
            <li className={styles['submenu-item']}>
              <NavLink>입양 후기</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>정보 공유</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>무료 나눔</NavLink>
            </li>
            <li className={styles['submenu-item']}>
              <NavLink>봉사 모집</NavLink>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
}
