import React from 'react';
import AdoptionReviewList from 'components/Community/AdoptionReview/AdoptionReviewList';
import Banner from 'components/Common/Banner/Banner';

const AdoptionMain = () => {
  return (
    <>
      <Banner title="입양 후기 게시판" />
      <AdoptionReviewList />
    </>
  );
};

export default AdoptionMain;
