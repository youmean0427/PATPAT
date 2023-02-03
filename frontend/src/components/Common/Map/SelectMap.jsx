import { useQuery } from '@tanstack/react-query';
import { getGugunList } from 'apis/api/shelter';
import { getRegionSelectList } from 'apis/utils/parsingRegionData';
import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { sidoState } from 'recoil/atoms/region';
import styles from './SelectMap.module.scss';
import SelectMapItem from './SelectMapItem';
export default function SelectMap({ list }) {
  const [sido, setSido] = useRecoilState(sidoState);

  const handleClickItem = (code, name) => {
    setSido({ ...sido, code, name });
  };
  console.log(sido);
  return (
    <div className={styles['map-wrapper']}>
      <div className={styles['info-box']}></div>
      <svg width="400" height="800" viewBox="30 100 400 800" xmlns="http://www.w3.org/2000/svg">
        <g>
          {getRegionSelectList(list).map(item => {
            return (
              <SelectMapItem
                onClick={() => {
                  handleClickItem(item.code, item.name);
                }}
                key={item.code}
                className={sido.code === item.code ? `${styles.land} ${styles.click}` : styles.land}
                name={item.name}
                d={item.d}
              />
            );
          })}
        </g>
      </svg>
    </div>
  );
}
