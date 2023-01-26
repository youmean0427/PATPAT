import { useQuery } from '@tanstack/react-query';
import Card from 'components/Common/Card';
import React from 'react';
import styles from './AbandonedDogItem.module.scss';
export default function AbandonedDogItem({ item }) {
  const { id, name, age, kind, imgUrl, kg, gender, isNeutered } = item;
  return (
    <Card>
      <img src={imgUrl} alt={name} />
      <div className={styles['description']}>
        <div className={styles.name}>{name}</div>
        <div className={styles.kind}>{kind}</div>
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
