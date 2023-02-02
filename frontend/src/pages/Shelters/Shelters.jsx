import { useQuery } from '@tanstack/react-query';
import { getRegionList } from 'apis/utils/parsingRegionData';
import { getCountShelterByBreed } from 'apis/api/shelter';
import React, { useState } from 'react';
import styles from './Shelters.module.scss';
import SheltersList from 'components/Shelters/List/SheltersList';

export default function Shelters() {
  const { data, isLoading } = useQuery({
    queryKey: ['searchBreedCountPerRegion'],
    queryFn: () => getCountShelterByBreed(null),
  });

  const [click, setClick] = useState(Array(17).fill(false));
  const [sido, setSido] = useState();

  console.log(data);
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
        <SheltersList />
      </div>
    </div>
  );
}
