import React from 'react';
import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';
import { useNavigate } from 'react-router-dom';

export default function MissingDogItem({ item }) {
  const navigate = useNavigate();
  const { missingId, gender, breedName, name, kg, age, thumbnail, neutered } = item;

  return (
    <Card>
      <div
        onClick={() =>
          navigate(`/mypage/missing/${missingId}`, {
            state: { missingId: missingId, name: name, thumbnail: thumbnail },
          })
        }
      >
        <img src={thumbnail.filePath} alt={name} />
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
      </div>
    </Card>
  );
}
