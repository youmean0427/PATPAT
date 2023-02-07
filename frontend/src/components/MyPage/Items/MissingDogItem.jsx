import React from 'react';
import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';
import { useNavigate } from 'react-router-dom';

export default function MissingDogItem({ item }) {
  const navigate = useNavigate();
  const {
    typeCode,
    missingId,
    personalProtectionId,
    userId,
    genderCode,
    gender,
    latitude,
    longitude,
    title,
    content,
    breedId,
    breedName,
    name,
    kg,
    age,
    categoryEar,
    categoryTail,
    categoryColor,
    categoryPattern,
    categoryCloth,
    categoryClothColor,
    fileUrlList,
    thumbnail,
    findDate,
    stateCode,
    neutered,
  } = item;

  console.log(name);
  return (
    <div
      className={styles.card}
      onClick={() =>
        navigate(`/mypage/mymissing/${missingId}`, {
          state: { missingId: missingId, name: name, thumbnail: thumbnail },
        })
      }
    >
      {typeCode === 0 ? (
        <Card>
          <img src={thumbnail} alt={name} />
          <div className={styles.description}>
            <div className={styles.name}>{name}</div>
            <div className={styles.kind}>{breedName}</div>
            <div className={styles.gender}>
              {gender}(중성화 {neutered ? 'O' : 'X'})
            </div>
            <div className={styles.age}>
              {age}살 / {kg}kg
            </div>
          </div>
        </Card>
      ) : null}
    </div>
  );
}
