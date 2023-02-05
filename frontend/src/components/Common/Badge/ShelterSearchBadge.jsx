import React from 'react';
import { useResetRecoilState, useSetRecoilState } from 'recoil';
import { gugunIsDisabledState, selectBreedState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';
import styles from './ShelterSearchBadge.module.scss';
export default function ShelterSearchBadge({ value, type, gugunSelectRef }) {
  const resetSido = useResetRecoilState(selectSidoState);
  const resetGugun = useResetRecoilState(selectGugunState);
  const resetBreed = useResetRecoilState(selectBreedState);
  const setGugunIsDisabled = useSetRecoilState(gugunIsDisabledState);
  const handleClickRemove = () => {
    if (type === 'sido') {
      resetSido();
      resetGugun();
      setGugunIsDisabled(true);
    } else if (type === 'gugun') {
      resetGugun();
    } else {
      resetBreed();
    }
  };
  //  type : sido , gugun, breed
  return (
    <button onClick={handleClickRemove} className={`${styles.badge} ${styles[type]}`}>
      {value}
    </button>
  );
}
