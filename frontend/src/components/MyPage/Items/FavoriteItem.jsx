import React from 'react';
import styles from './FavoriteItem.module.scss';
import Card from 'components/Common/Card';
import FavImg from 'assets/images/fav-img.png';
import { deleteFavProtect } from 'apis/api/user';

export default function FavoriteItem({ item }) {
  const { protectId, userId, image, name, state } = item;

  return (
    <div className={styles.card}>
      <Card>
        <div>
          <div className={state === 0 ? styles.state0 : state === 1 ? styles.state1 : styles.state2}>
            {state === 0 ? '공고' : state === 1 ? '보호중' : '입양완료'}
          </div>
          <img src={image} alt="" />
          <img
            onclick={() => deleteFavProtect(protectId)}
            title="꾹 해제"
            className={styles['fav-img']}
            src={FavImg}
            alt=""
          />
        </div>
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
        </div>
      </Card>
    </div>
  );
}
