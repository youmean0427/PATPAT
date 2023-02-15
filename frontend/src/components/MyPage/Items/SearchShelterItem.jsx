import React from 'react';
import styles from './SearchShelterItem.module.scss';
import Card from 'components/Common/Card';
import ProtectStateBadge from 'components/Common/Badge/ProtectStateBadge';
import { useNavigate } from 'react-router';

export default function SearchShelterItem({ item }) {
  const navigate = useNavigate();

  return (
    <Card>
      <div className={styles.card} onClick={() => navigate(`/protects/${item.protectId}`)}>
        <img src={item.thumbnail} alt={item.protectName} />
        <div className={styles.description}>
          <div className={styles.name}>{item.protectName}</div>
          <div className={styles.kind}>{item.breedName}</div>
          <div className={styles.gender}>
            {item.gender}(중성화 {item.neutered})
          </div>
          <div className={styles.age}>
            {item.age}살 / {item.weight}kg
          </div>
        </div>
        <ProtectStateBadge state={item.state} stateCode={item.stateCode} />
      </div>
    </Card>
  );
}
