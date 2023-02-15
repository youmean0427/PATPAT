import React from 'react';
import InfoList from 'components/Community/Info/InfoList';
import Banner from 'components/Common/Banner/Banner';

const InfoMain = props => {
  return (
    <>
      <Banner title="정보 공유 게시판" />
      <InfoList />
    </>
  );
};

export default InfoMain;
