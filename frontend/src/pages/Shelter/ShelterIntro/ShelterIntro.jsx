import { useQuery } from '@tanstack/react-query';
import { getShelterDetail } from 'apis/api/shelter';
import React from 'react';
import { useParams } from 'react-router-dom';
import { Carousel } from 'react-carousel-minimal';
import './ShelterIntro.scss';
import styles from './ShelterIntro.module.scss';
import test1 from 'assets/images/test/shelter_test_img1.jpeg';
import test2 from 'assets/images/test/shelter_test_img2.jpeg';
import test3 from 'assets/images/test/shelter_test_img3.jpeg';
import test4 from 'assets/images/test/shelter_test_img4.jpeg';
import ShelterContainer from 'containers/ShelterContainer';
export default function ShelterIntro() {
  const { shelterId } = useParams();

  const imageData = [{ image: test1 }, { image: test2 }, { image: test3 }, { image: test4 }];

  const slideNumberStyle = {
    fontSize: '20px',
    fontWeight: 'bold',
  };

  const { data, isLoading } = useQuery(['getShelterDetailInfo', shelterId], () => getShelterDetail(shelterId), {
    staleTime: 1000 * 60 * 5,
  });
  if (isLoading) return;
  return (
    <ShelterContainer title="보호소 정보">
      <main className={styles.main}>
        <Carousel
          data={imageData}
          radius="10px"
          slideNumber={true}
          slideNumberStyle={slideNumberStyle}
          dots={true}
          pauseIconColor="white"
          pauseIconSize="40px"
          slideBackgroundColor="darkgrey"
          slideImageFit="cover"
          thumbnails={true}
          thumbnailWidth="100px"
        ></Carousel>
        <div className={styles['info-box']}>
          <div>{data.name}</div>
          <div>{data.address}</div>
          <div>문석환</div>
          <div>{data.phoneNum}</div>
        </div>
      </main>
    </ShelterContainer>
  );
}
