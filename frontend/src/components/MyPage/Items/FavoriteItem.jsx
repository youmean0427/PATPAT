import React, { useState } from 'react';
import styles from './FavoriteItem.module.scss';
import Card from 'components/Common/Card';
import FavImg from 'assets/images/fav-img.png';
import UnFavImg from 'assets/images/unfav-img.png';
import { insertFavProtect, deleteFavProtect } from 'apis/api/user';

export default function FavoriteItem({ item }) {
  const { spDogId, userId, name, imageUrl, stateCode, state, weight, neutered, gender, genderCode, age } = item;
  const [favorite, setFavorite] = useState(true);

  const handleFavBtn = () => {
    favorite ? deleteFavProtect(spDogId) : insertFavProtect(spDogId);
    setFavorite(cur => !cur);
  };

  return (
    <div className={styles.card}>
      <Card>
        <div>
          <div className={state === 0 ? styles.state0 : state === 1 ? styles.state1 : styles.state2}>
            {state === 0 ? '공고' : state === 1 ? '보호중' : '입양완료'}
          </div>
          <img src={imageUrl} alt="" />
          <img
            onClick={handleFavBtn}
            title="꾹 버튼"
            className={styles['fav-img']}
            src={favorite ? FavImg : UnFavImg}
            alt=""
          />
        </div>
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
          <div className={styles.gender}>
            {gender}(중성화 {genderCode ? 'O' : 'X'})
          </div>
          <div className={styles.age}>
            {age}살 / {weight}kg
          </div>
        </div>
      </Card>
    </div>
  );
}
