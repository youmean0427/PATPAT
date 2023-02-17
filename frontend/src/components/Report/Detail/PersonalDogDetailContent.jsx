import React, { useState } from 'react';
import styles from './PersonalDogDetailContent.module.scss';
import { useQuery } from '@tanstack/react-query';
import { getPersonalDogDetail } from 'apis/api/report';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';
import { MapMarker, Map } from 'react-kakao-maps-sdk';
import HtmlReactParser from 'html-react-parser';
import defaultPicture from '../../../assets/images/volunteer.png';
import infoIcon from 'assets/images/forpaw-info.png';
import DetailModal from 'components/Common/DetailModal';
import EarDetail from 'components/Report/Create/EarDetail';
import PatternDetail from 'components/Report/Create/PatternDetail';
import TailDetail from 'components/Report/Create/TailDetail';

export default function PersonalDogDetailContent({ item, state }) {
  // const [user, setUser] = useState('');
  const [file2, setFile2] = useState(0);
  const [file3, setFile3] = useState(0);
  const [modal, setModal] = useState(false);
  const [modalNum, setModalNum] = useState();

  const openModal = idx => {
    setModalNum(idx);
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };

  const { isLoading, data } = useQuery({
    queryKey: ['PersonalDogDetail'],
    queryFn: () => getPersonalDogDetail(item),
  });
  if (isLoading) return;

  let user = 'user';
  if (localStorage.getItem('user')) {
    user = JSON.parse(localStorage.getItem('user'));
  }

  return (
    <div>
      <header className={styles['container-title']}>
        <div className={styles.title}>{data.title}</div>
        <div>
          <div className={styles['container-title-inner']}>
            <div className={styles['container-title-inner-user']}>
              {/* <span className={styles.writer}>{data.userId}</span>
              <span className={styles.date}>23.02.03</span> */}
            </div>

            {data.userId === user.userId && data.stateCode !== 6 ? (
              <div>
                <Link to="update" state={{ data, state }}>
                  <Button variant="contained" className={styles.button}>
                    수정
                  </Button>
                </Link>
              </div>
            ) : null}
          </div>
        </div>
        <hr />
      </header>
      <div className={styles.container}>
        <div className={styles['container-info-picture']}>
          <div className={styles['container-info-picture-inner']}>
            <div>
              <div className={styles.thumbnail}>
                {data.fileUrlList[0] === undefined ? (
                  <div>
                    <img src={defaultPicture} alt="" />
                  </div>
                ) : (
                  <div>
                    {file2 === 1 ? (
                      <img src={data.fileUrlList[1].filePath} alt="" />
                    ) : file3 === 1 ? (
                      <img src={data.fileUrlList[2].filePath} alt="" />
                    ) : (
                      <img src={data.fileUrlList[0].filePath} alt="" />
                    )}
                  </div>
                )}
              </div>
            </div>

            <div className={styles['container-info-picture-inner-sub']}>
              <div>
                {data.fileUrlList[1] === undefined ? (
                  <img src={defaultPicture} alt="" />
                ) : (
                  <img
                    src={data.fileUrlList[1].filePath}
                    alt=""
                    onMouseEnter={() => {
                      setFile2(1);
                    }}
                    onMouseLeave={() => {
                      setFile2(0);
                    }}
                  />
                )}
              </div>
              <div>
                {data.fileUrlList[2] === undefined ? (
                  <img src={defaultPicture} alt="" />
                ) : (
                  <img
                    src={data.fileUrlList[2].filePath}
                    alt=""
                    onMouseEnter={() => {
                      setFile3(1);
                    }}
                    onMouseLeave={() => {
                      setFile3(0);
                    }}
                  />
                )}
              </div>
            </div>
          </div>
        </div>
        <div className={styles['container-content']}>
          <div className={styles['container-content-title']}>
            <div className={styles.name}>{data.name}</div>
            {data.stateCode === 1 ? (
              <div className={styles.stateButtonOrange}>임시보호</div>
            ) : (
              <div className={styles.stateButtonGreen}>완료</div>
            )}
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
              <div>나이</div>
              {data.age === 0 ? <span>모름</span> : <span>{data.age}살</span>}
            </div>
            <div>
              <div>몸무게</div>
              {data.kg === 0 ? <span>모름</span> : <span>{data.kg}kg</span>}
            </div>
            <div>
              <div>중성화</div>
              <span>{data.neutered}</span>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.subTitle}>카테고리</div>
      <hr />
      <div className={styles['container-content-character']}>
        <div className={styles['container-content-character-list']}>
          <div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(0)} />귀
            </div>
            <span>{data.categoryEar}</span>
          </div>
          <div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(1)} />
              무늬
            </div>
            <span>{data.categoryPattern}</span>
          </div>
          <div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(2)} />
              꼬리
            </div>
            <span>{data.categoryTail}</span>
          </div>
        </div>

        <div className={styles['container-content-character-list']}>
          <div>
            <div>옷착용</div>
            <span>{data.categoryCloth}</span>
          </div>
          <div>
            <div>털색</div>
            <span>
              {data.categoryPatternCode === 1 ? (
                <div className={styles.colorPicker}>
                  <input
                    className={styles.colorPickerHalf}
                    type="color"
                    defaultValue={data.categoryColor[0]}
                    disabled
                  />
                </div>
              ) : data.categoryPatternCode === 2 ? (
                <div className={styles.colorPicker}>
                  <div>
                    <input
                      className={styles.colorPickerHalf}
                      type="color"
                      defaultValue={data.categoryColor[0]}
                      disabled
                    />
                  </div>
                  <div>
                    <input
                      className={styles.colorPickerHalf}
                      type="color"
                      defaultValue={data.categoryColor[1]}
                      disabled
                    />
                  </div>
                </div>
              ) : data.categoryPatternCode === 3 ? (
                <div className={styles.colorPicker}>
                  <div>
                    <input
                      className={styles.colorPickerHalf}
                      type="color"
                      defaultValue={data.categoryColor[0]}
                      disabled
                    />
                  </div>
                  <div>
                    <input
                      className={styles.colorPickerHalf}
                      type="color"
                      defaultValue={data.categoryColor[1]}
                      disabled
                    />
                  </div>
                  <div>
                    <input
                      className={styles.colorPickerHalf}
                      type="color"
                      defaultValue={data.categoryColor[2]}
                      disabled
                    />
                  </div>
                </div>
              ) : (
                <span className={styles.noColor}> 없음</span>
              )}
            </span>
          </div>
          <div></div>
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

      <div className={styles.content}>
        <div>{data.content === null ? null : HtmlReactParser(data.content)}</div>
      </div>
      <DetailModal
        open={modal}
        close={closeModal}
        title={modalNum === 0 ? '귀 모양 상세' : modalNum === 1 ? '무늬 상세' : '꼬리 모양 상세'}
      >
        {modalNum === 0 ? <EarDetail /> : modalNum === 1 ? <PatternDetail /> : <TailDetail />}
      </DetailModal>
    </div>
  );
}
