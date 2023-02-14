import { useQuery } from '@tanstack/react-query';
import { getUserInfo } from 'apis/api/user';
import React from 'react';
import { Outlet } from 'react-router';
import styles from './MyPage.module.scss';
import ForPawMeter from 'components/MyPage/ForPawMeter';
import Navbar from 'components/MyPage/Navbar/Navbar';
import MenuLink from 'components/MyPage/Navbar/MenuLink';

export default function MyPage() {
  const { data, isLoading } = useQuery({
    queryKey: ['getUserInfo'],
    queryFn: () => getUserInfo(),
  });

  if (isLoading) return;

  console.log(data);
  return (
    <div className={styles.container}>
      <ForPawMeter data={data} />
      <Navbar>
        <MenuLink move="missing" value="내 가족 찾기" />
        <MenuLink move="favorite" value="꾹 리스트" />
        <MenuLink move="boards" value="게시판 관리" />
        <MenuLink move="volunteer" value="봉사 신청" />
        <MenuLink move="consulting" value="상담 신청" />
      </Navbar>
      <Outlet />
    </div>
  );
}
