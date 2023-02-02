import React from 'react';
import AdoptionReviewList from 'components/Community/AdoptionReview/AdoptionReviewList';
import Banner from 'components/Common/Banner/Banner';
import SideMenu from 'components/Common/SideMenu/SideMenu';

const AdoptionMain = () => {
  return (
    <>
      <SideMenu />
      <Banner title="입양 후기 게시판" />
      <AdoptionReviewList />
    </>
  );
};

export default AdoptionMain;
