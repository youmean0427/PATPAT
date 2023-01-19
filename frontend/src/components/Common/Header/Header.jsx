import React from 'react';
import styles from './Header.module.scss';
import logo from 'assets/images/logo.png';
import { NavLink } from 'react-router-dom';
import { DarkModeOutlined } from '@mui/icons-material';
export default function Header() {
  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <div className={styles.container}>
          <div className={styles['container-inner']}>
            {/* flex */}
            <div className={styles.left}>
              <NavLink className={styles['logo-link']} to="/">
                <div className={styles['logo-box']}>
                  <img src={logo} alt="logo" />
                </div>
              </NavLink>
              <div className={styles['menu-box']}>
                <ul className={styles.menu}>
                  <li className={styles['menu-item']}>
                    소개
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
                    보호소
                    <ul className={styles.submenu}>
                      <li className={styles['submenu-item']}>
                        <NavLink>보호소 찾기</NavLink>
                      </li>
                    </ul>
                  </li>
                  <li className={styles['menu-item']}>
                    신고
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
                    봉사
                    <ul className={styles.submenu}>
                      <li className={styles['submenu-item']}>
                        <NavLink>봉사 신청</NavLink>
                      </li>
                    </ul>
                  </li>
                  <li className={styles['menu-item']}>
                    커뮤니티
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
            </div>
            <div className={styles.right}>
              <DarkModeOutlined />
              <button className={styles.login}>로그인</button>
            </div>
          </div>
        </div>
        <div id={styles['mobile-menu']}>
          {/* 모바일 크기가 되었을 때 햄버거 클릭시 나올 메뉴 */}
        </div>
      </nav>
    </header>
  );
}
