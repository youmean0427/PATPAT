import { useQuery } from '@tanstack/react-query';
import { getRegionList } from 'apis/utils/parsingRegionData';
import { getCountShelterByBreed } from 'apis/api/shelter';
import React, { useState } from 'react';
import styles from './Contents.module.scss';
import GugunList from './List/GugunList';
import BreedsList from './List/BreedsList';
import SheltersList from './List/SheltersList';

export default function Contents() {
  const { data, isLoading } = useQuery({
    queryKey: ['searchBreedCountPerRegion'],
    queryFn: () => getCountShelterByBreed(1),
  });
  const [click, setClick] = useState(Array(17).fill(false));
  const [sido, setSido] = useState();
  const [gugun, setGugun] = useState();
  const [breed, setBreed] = useState();

  if (isLoading) return;

  return (
    <div className={styles.container}>
      <div className={styles['map-wrapper']}>
        <svg className={styles.svg} viewBox="30 100 400 800" xmlns="http://www.w3.org/2000/svg">
          <g>
            {getRegionList(data.list).map((item, idx) => {
              return (
                <path
                  key={item.code}
                  className={click === undefined ? styles.land : click[idx] ? styles['land-click'] : styles.land}
                  onClick={() => {
                    const newClick = Array(17).fill(false);
                    newClick[idx] = true;
                    setClick(newClick);
                    setSido(item.code);
                  }}
                  title={item.name}
                  d={item.d}
                />
              );
            })}
          </g>
        </svg>
      </div>
      <div className={styles.lists}>
        <div className={styles.selects}>
          {sido === undefined ? null : <GugunList setGugun={setGugun} sido={sido} />}
          <BreedsList setBreed={setBreed} />
        </div>
        <SheltersList sido={sido} gugun={gugun} breed={breed} />
      </div>
    </div>
  );
}
