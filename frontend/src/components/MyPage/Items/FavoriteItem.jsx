import React, { useState } from 'react';
import styles from './FavoriteItem.module.scss';
import Card from 'components/Common/Card';
import PetsIcon from '@mui/icons-material/Pets';
import { insertFavProtect, deleteFavProtect } from 'apis/api/user';

export default function FavoriteItem({ item }) {
  const { spDogId, userId, name, imageUrl, stateCode, state, weight, neutered, neuteredCode, gender, genderCode, age } =
    item;
  const [favorite, setFavorite] = useState(true);

  const handleFavBtn = () => {
    favorite ? deleteFavProtect(spDogId) : insertFavProtect(spDogId);
    setFavorite(cur => !cur);
  };

  return (
    <Card>
      <img src={imageUrl} alt={name} />
      <div
        className={`${styles.state} ${
          stateCode === 0
            ? styles.state0
            : stateCode === 1
            ? styles.state1
            : stateCode === 2
            ? styles.state2
            : styles.state3
        }`}
      >
        {state}
      </div>
      <div className={styles.description}>
        <div className={styles.detail}>
          <div className={styles.name}>{name}</div>
          {/* <div className={styles.kind}>{breedName}</div> */}
          <div className={styles.gender}>
            {gender}(중성화 {neutered ? 'O' : 'X'})
          </div>
          <div className={styles.age}>
            {age}살 / {weight}kg
          </div>
        </div>
        <div className={styles['fav-icon']}>
          {favorite ? (
            <PetsIcon onClick={() => handleFavBtn()} sx={{ fontSize: '55px', color: 'hotpink' }} />
          ) : (
            <PetsIcon onClick={() => handleFavBtn()} sx={{ fontSize: '55px', color: 'lightgray' }} />
          )}
        </div>
      </div>
    </Card>
  );
}
