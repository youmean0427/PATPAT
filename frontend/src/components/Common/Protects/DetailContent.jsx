import React, { useState, useEffect } from 'react';
import styles from './DetailContent.module.scss';
import infoIcon from 'assets/images/forpaw-info.png';
import DetailModal from 'components/Common/DetailModal';
import EarDetail from 'components/Report/Create/EarDetail';
import PatternDetail from 'components/Report/Create/PatternDetail';
import TailDetail from 'components/Report/Create/TailDetail';
import PetsIcon from '@mui/icons-material/Pets';
import { insertFavProtect, deleteFavProtect } from 'apis/api/user';

export default function DetailContent({ data }) {
  const [modal, setModal] = useState(false);
  const [modalNum, setModalNum] = useState();
  const [userId, setUserId] = useState();
  const [favorite, setFavorite] = useState(data.isFavorite);

  const openModal = idx => {
    setModalNum(idx);
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };

  const handleFavBtn = () => {
    favorite ? deleteFavProtect(data.protectId) : insertFavProtect(data.protectId);
    setFavorite(cur => !cur);
  };

  useEffect(() => {
    if (localStorage.getItem('user') !== null) {
      setUserId(JSON.parse(localStorage.getItem('user')).userId);
    }
  }, []);

  return (
    <>
      <div className={styles.content1}>
        <div className={styles['dog-name']}>
          <p>{data.protectName}</p>{' '}
          {userId !== undefined ? (
            <div title={favorite ? '꾹 해제' : '꾹 등록'} className={styles.icon}>
              {favorite ? (
                <PetsIcon
                  className={styles.pressed}
                  onClick={() => handleFavBtn()}
                  sx={{ fontSize: '4rem', color: 'hotpink' }}
                />
              ) : (
                <PetsIcon
                  className={styles.pressed}
                  onClick={() => handleFavBtn()}
                  sx={{ fontSize: '4rem', color: 'lightgray' }}
                />
              )}
            </div>
          ) : null}
        </div>
        <div
          className={
            data.stateCode === 0
              ? styles.state0
              : data.stateCode === 1
              ? styles.state1
              : data.stateCode === 2
              ? styles.state2
              : styles.state3
          }
        >
          {data.state}
        </div>
      </div>
      <hr className={styles.line} />
      <div className={styles.content2}>
        <div className={styles['dog-detail']}>
          <p>종/품종 :</p>
          <p>성별 :</p>
          <p>추정나이 :</p>
          <p>몸무게 :</p>
          <p>중성화 여부 :</p>
        </div>
        <div className={styles['dog-detail-ans']}>
          <p>{data.breedName}</p>
          <p>{data.gender}</p>
          <p>{data.age}</p>
          <p>{data.kg}kg</p>
          <p>{data.neutered}</p>
        </div>
      </div>
      <div className={styles.content3}>
        <div className={styles.category1}>
          <div className={styles['dog-category']}>
            <p>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(0)} />귀 :
            </p>
            <p>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(1)} />
              무늬 :
            </p>
            <p>
              <img src={infoIcon} alt="" className={styles['info-icon']} style={{ visibility: 'hidden' }} />
              옷착용 :
            </p>
          </div>
          <div className={styles['dog-category-ans']}>
            <p>{data.categoryEar}</p>
            <p>{data.categoryPattern}</p>
            <p>{data.categoryCloth}</p>
          </div>
        </div>
        <div className={styles.category2}>
          <div className={styles['dog-category']}>
            <p>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(2)} />
              꼬리 :
            </p>
            <p>
              <img src={infoIcon} alt="" className={styles['info-icon']} style={{ visibility: 'hidden' }} />
              털색 :
            </p>
            <p style={{ visibility: 'hidden' }}>None</p>
          </div>
          <div className={styles['dog-category-ans']}>
            <p>{data.categoryTail}</p>
            <div className={styles.colors}>
              {data.categoryColor.map((item, index) => (
                <div key={index} className={styles['color-box']} style={{ backgroundColor: item }}></div>
              ))}
            </div>
            <p style={{ visibility: 'hidden' }}>None</p>
          </div>
        </div>
      </div>
      <div className={styles['']}></div>
      <DetailModal
        open={modal}
        close={closeModal}
        title={modalNum === 0 ? '귀 모양 상세' : modalNum === 1 ? '무늬 상세' : '꼬리 모양 상세'}
      >
        {modalNum === 0 ? <EarDetail /> : modalNum === 1 ? <PatternDetail /> : <TailDetail />}
      </DetailModal>
    </>
  );
}
