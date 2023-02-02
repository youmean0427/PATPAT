import React from 'react';
import styles from './FavoriteItem.module.scss';
import Card from 'components/Common/Card';
import FavImg from 'assets/images/fav-img.png';

export default function FavoriteItem({ item }) {
  const { image, name, stateCode } = item;

  return (
    <div className={styles.card}>
      <Card>
        <div>
          <div className={stateCode === 0 ? styles.state0 : stateCode === 1 ? styles.state1 : styles.state2}>
            {stateCode === 0 ? '공고' : stateCode === 1 ? '보호중' : '입양완료'}
          </div>
          <img src={image} alt="" />
          <img title="꾹 해제" className={styles['fav-img']} src={FavImg} alt="" />
        </div>
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
        </div>
      </Card>
    </div>
  );
}
