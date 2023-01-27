import React from 'react';
import MenuItem from './MenuItem';
import styles from './MenuList.module.scss';
export default function MenuList() {
  return (
    <div className={styles['menu-box']}>
      <ul className={styles.menu}>
        <MenuItem value="소개" submenu={['인사말', '미션 & 비전', '활동 내역', '통계']} />
        <MenuItem value="보호소" submenu={['보호소 찾기']} />
        <MenuItem value="신고" submenu={['실종 신고', '임시보호 신고']} />
        <MenuItem value="봉사" submenu={['봉사 신청']} />
        <MenuItem value="커뮤니티" submenu={['입양 후기', '정보 공유', '무료 나눔', '봉사 모집']} />
      </ul>
    </div>
  );
}
