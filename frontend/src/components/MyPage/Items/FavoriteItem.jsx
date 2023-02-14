import React, { useState } from 'react';
import styles from './FavoriteItem.module.scss';
import Card from 'components/Common/Card';
import PetsIcon from '@mui/icons-material/Pets';
import { insertFavProtect, deleteFavProtect } from 'apis/api/user';
import { useNavigate } from 'react-router-dom';
import ProtectStateBadge from 'components/Common/Badge/ProtectStateBadge';

export default function FavoriteItem({ item }) {
  const { spDogId, breedName, name, imageUrl, stateCode, state, weight, neutered, gender, age } = item;
  const [favorite, setFavorite] = useState(true);
  const navigate = useNavigate();

  const handleFavBtn = () => {
    favorite ? deleteFavProtect(spDogId) : insertFavProtect(spDogId);
    setFavorite(cur => !cur);
  };

  return (
    <Card>
      <div className={styles.card} onClick={() => navigate(`/protects/${spDogId}`)}>
        <img src={imageUrl} alt={name} />
        <div className={styles.description}>
          <div className={styles.name}>{name}</div>
          <div className={styles.kind}>{breedName}</div>
          <div className={styles.gender}>
            {gender}(중성화 {neutered ? 'O' : 'X'})
          </div>
          <div className={styles.age}>
            {age}살 / {weight}kg
          </div>
        </div>
        <ProtectStateBadge state={state} stateCode={stateCode} />
        <div className={styles['fav-icon']} title={favorite ? '꾹 해제' : '꾹 등록'}>
          {favorite ? (
            <PetsIcon
              className={styles.icon}
              onClick={() => handleFavBtn()}
              sx={{ fontSize: '55px', color: 'hotpink' }}
            />
          ) : (
            <PetsIcon
              className={styles.icon}
              onClick={() => handleFavBtn()}
              sx={{ fontSize: '55px', color: 'lightgray' }}
            />
          )}
        </div>
      </div>
    </Card>
  );
}
