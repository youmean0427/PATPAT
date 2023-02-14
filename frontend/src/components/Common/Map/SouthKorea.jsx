import React from 'react';
import styles from './SouthKorea.module.scss';
import { getRegionList } from 'apis/utils/parsingRegionData';
import { useNavigate } from 'react-router-dom';
import SouthKoreaItem from './SouthKoreaItem';
import { useSetRecoilState } from 'recoil';
import { selectBreedState, selectSidoState } from 'recoil/atoms/shelter';
export default function SouthKorea({ breedId, breedName, list }) {
  const navigate = useNavigate();
  const setSido = useSetRecoilState(selectSidoState);
  const setBreed = useSetRecoilState(selectBreedState);
  return (
    <div className={styles['map-wrapper']}>
      <div className={styles.info}></div>
      <svg width="400" height="800" viewBox="30 100 400 800" xmlns="http://www.w3.org/2000/svg">
        <g>
          {getRegionList(list).map(item => {
            const color = item.color;
            return (
              <SouthKoreaItem
                onClick={() => {
                  setSido({ sidoCode: item.sidoCode, name: item.sidoName });
                  setBreed({ breedId, name: breedName });
                  navigate('/shelter/search', { state: { breedId, breedName, sidoCode: item.sidoCode } });
                }}
                key={item.sidoCode}
                className={`${styles.land} ${styles[color]}`}
                count={item.count}
                title={item.sidoName}
                d={item.d}
              />
            );
          })}
        </g>
      </svg>
    </div>
  );
}
