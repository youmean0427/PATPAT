import React from 'react';
import FreeShareList from 'components/Community/FreeShare/FreeShareList';
import Banner from 'components/Common/Banner/Banner';

export default function FreeShareMain() {
  return (
    <>
      <Banner title="무료 나눔 게시판" />
      <FreeShareList />
    </>
  );
}
