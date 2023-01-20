import React from 'react';
import styles from './SideMenu.module.scss';
import SideMenuItem from './SideMenuItem';
export default function SideMenu() {
  return (
    <div className={styles.wrap}>
      <SideMenuItem title="마이 페이지" url="/mypage" icon="mypage" />
      <SideMenuItem title="보호소 등록" url="/shelter/regist" icon="shelter" />
      <SideMenuItem title="실종 신고" url="/report/miss" icon="report" />
      <SideMenuItem
        title="봉사 신청"
        url="/volunteer/regist"
        icon="volunteer"
      />
    </div>
  );
}
