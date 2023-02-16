import ProtectStateBadge from 'components/Common/Badge/ProtectStateBadge';
import Card from 'components/Common/Card';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './AbandonedDogItem.module.scss';
export default function AbandonedDogItem({ item }) {
  const { protectId, name, age, breedName, thumbnail, kg, gender, neutered, protectName, state, stateCode } = item;
  const navigate = useNavigate();
  return (
    <Card>
      <div onClick={() => navigate(`/protects/${protectId}`)}>
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
      </div>
    </Card>
  );
}
