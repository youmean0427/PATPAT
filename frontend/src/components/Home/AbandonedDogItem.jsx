import Card from 'components/Common/Card';
import React from 'react';
import styles from './AbandonedDogItem.module.scss';
export default function AbandonedDogItem({ item }) {
  const { name, age, breed, thumbnail, kg, gender, isNeutered } = item;
  return (
    <Card>
      <img src={thumbnail} alt={name} />
      <div className={styles.description}>
        <div className={styles.name}>{name}</div>
        <div className={styles.kind}>{breed}</div>
        <div className={styles.gender}>
          {gender}(중성화 {isNeutered ? 'O' : 'X'})
        </div>
        <div className={styles.age}>
          {age}살 / {kg}kg
        </div>
      </div>
    </Card>
  );
}
