import React from 'react';
import styles from './SouthKorea.module.scss';
import { getRegionList } from 'apis/utils/parsingRegionData';
import MapItem from './MapItem';
export default function SouthKorea({ list }) {
  return (
    <div className={styles['map-wrapper']}>
      <div className={styles['info-box']}></div>
      <svg width="400" height="800" viewBox="30 100 400 800" xmlns="http://www.w3.org/2000/svg">
        <g>
          {getRegionList(list).map(item => {
            const color = item.color;
            return (
              <MapItem
                key={item.code}
                className={`${styles.land} ${styles[color]}`}
                count={item.count}
                title={item.name}
                d={item.d}
              />
            );
          })}
        </g>
      </svg>
    </div>
  );
}
