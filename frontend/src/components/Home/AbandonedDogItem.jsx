import ProtectStateBadge from 'components/Common/Badge/ProtectStateBadge';
import Card from 'components/Common/Card';
import React from 'react';
import styles from './AbandonedDogItem.module.scss';
export default function AbandonedDogItem({ item }) {
  const { name, age, breedName, thumbnail, kg, gender, neutered, protectName, state, stateCode } = item;
  return (
    <Card>
      <img src={thumbnail} alt={name} />
      <div className={styles.description}>
        <div className={styles.name}>{protectName}</div>
        <div className={styles.kind}>{breedName}</div>
        <div className={styles.gender}>
          {gender}(중성화 {neutered ? 'O' : 'X'})
        </div>
        <div className={styles.age}>
          {age}살 / {kg}kg
        </div>
      </div>
      <ProtectStateBadge state={state} stateCode={stateCode} />
    </Card>
  );
}
