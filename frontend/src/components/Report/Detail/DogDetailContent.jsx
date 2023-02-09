import React, { useState } from 'react';
import styles from './DogDetailContent.module.scss';
import { useQuery } from '@tanstack/react-query';
import { getMissingDogDetail } from 'apis/api/report';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';
import { MapMarker, Map } from 'react-kakao-maps-sdk';
import HtmlReactParser from 'html-react-parser';

export default function DogDetailContent({ item, state }) {
  const { isLoading, data } = useQuery({
    queryKey: ['missingDogDetail'],
    queryFn: () => getMissingDogDetail(item),
  });

  if (isLoading) return;
  // console.log(data.latitude);
  return (
    <div>
      <header className={styles['container-title']}>
        <div className={styles.title}>{data.title}</div>
        <div>
          <div className={styles['container-title-inner']}>
            <div className={styles['container-title-inner-user']}>
              <span className={styles.writer}>{data.userId}</span>
              <span className={styles.date}>23.02.03</span>
            </div>
            <div>
              <Link to="update" state={{ data, state }}>
                <Button variant="contained" className={styles.button}>
                  수정
                </Button>
              </Link>
            </div>
          </div>
        </div>
        <hr />
      </header>
      <div className={styles.container}>
        <div className={styles['container-inner']}>
          <div className={styles['container-picture']}>
            <div className={styles.thumbnail}>
              <img src={data.fileUrlList[0]} alt="" />
            </div>
            <div className={styles['container-subpicture']}>
              <div className={styles['container-subpicture-inner']}>
                <div>
                  <img src={data.fileUrlList[1]} alt="" />
                </div>
                <div>
                  <img src={data.fileUrlList[2]} alt="" />
                </div>
              </div>
            </div>
          </div>

          <div className={styles['container-content']}>
            <div className={styles['container-content-title']}>
              <div className={styles.name}>{data.name}</div>
              {state === 0 ? <div className={styles.stateButtonRed}>실종</div> : null}
              {state === 1 ? <div className={styles.stateButtonOrange}>임시보호</div> : null}
            </div>
            <hr />
            <div className={styles['container-content-info']}>
              <div>
                <div>견종</div>
                <span>{data.breedName}</span>
              </div>
              <div>
                <div>성별</div>
                <span>{data.gender}</span>
              </div>
              <div>
                <div>추정나이</div>
                <span>{data.age}</span>
              </div>
              <div>
                <div>몸무게</div>
                <span>{data.kg}</span>
              </div>
              <div>
                <div>중성화</div>
                <span>{data.neutered}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.subTitle}>카테고리</div>
      <hr />
      <div className={styles['container-content-character']}>
        <div className={styles['container-content-character-list']}>
          <div>
            <div>귀</div>
            <span>{data.categoryEar}</span>
          </div>
          <div>
            <div>털색</div>
            <span>{data.categoryColor}</span>
          </div>
          <div>
            <div>무늬색</div>
            <span>{data.categoryPattern}</span>
          </div>
        </div>
        <div className={styles['container-content-character-list']}>
          <div>
            <div>꼬리</div>
            <span>{data.categoryTail}</span>
          </div>
          <div>
            <div>옷착용</div>
            <span>{data.categoryCloth}</span>
          </div>
          <div>
            <div>옷색</div>
            <span>{data.categoryClothColor}</span>
          </div>
        </div>
      </div>
      <div className={styles.subTitle}>실종 장소</div>
      <hr />
      <div className={styles.map}>
        <Map // 지도를 표시할 Container
          center={
            // 지도의 중심좌표
            { lat: data.latitude, lng: data.longitude }
          }
          style={{
            width: '100%',
            height: '100%',
          }}
          level={4} // 지도의 확대 레벨
        >
          <MapMarker
            position={{ lat: data.latitude, lng: data.longitude }}
            image={{
              src: 'https://i.ibb.co/z42FXX4/002-2.png', // 마커이미지의 주소입니다
              size: {
                width: 64,
                height: 64,
              }, // 마커이미지의 크기입니다
              options: {
                offset: {
                  x: 27,
                  y: 69,
                }, // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
              },
            }}
          />
        </Map>
      </div>
      <div className={styles.subTitle}>상세정보</div>
      <hr />
      {/* HTMl null 값 못받음 */}
      <div className={styles.content}>
        <div>{HtmlReactParser(data.content)}</div>
      </div>
    </div>
  );
}
