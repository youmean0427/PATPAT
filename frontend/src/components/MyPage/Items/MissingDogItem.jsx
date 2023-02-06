import React from 'react';
import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';

export default function MissingDogItem({ item }) {
  const { name, missingId, gender, isNeutered, age, breedId, breedName, kg, thumbnail } = item;

  return (
    <div className={styles.card}>
      <Card>
        <img src={thumbnail} alt={name} />
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
          <div className={styles.kind}>{breedName}</div>
          <div className={styles.gender}>
            {gender}(중성화 {isNeutered ? 'O' : 'X'})
          </div>
          <div className={styles.age}>
            {age}살 / {kg}kg
          </div>
        </div>
      </Card>
    </div>
  );
}
