import { getRegionSelectList } from 'apis/utils/parsingRegionData';
import React from 'react';
import { useRecoilState, useResetRecoilState, useSetRecoilState } from 'recoil';
import { searchShelterPageState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';
import styles from './SelectMap.module.scss';
import SelectMapItem from './SelectMapItem';
export default function SelectMap({ list }) {
  const [sido, setSido] = useRecoilState(selectSidoState);
  const resetGugun = useResetRecoilState(selectGugunState);
  const setPage = useSetRecoilState(searchShelterPageState);
  const handleClickItem = (code, name) => {
    setPage(1);
    setSido({ sidoCode: code, name });
    resetGugun();
  };
  return (
    <div className={styles['map-wrapper']}>
      <div className={styles.info}></div>
      <svg width="400" height="800" viewBox="30 100 400 800" xmlns="http://www.w3.org/2000/svg">
        <g>
          {getRegionSelectList(list).map(item => {
            return (
              <SelectMapItem
                onClick={() => {
                  handleClickItem(item.code, item.name);
                }}
                key={item.code}
                className={sido.sidoCode === item.code ? `${styles.land} ${styles.click}` : styles.land}
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
