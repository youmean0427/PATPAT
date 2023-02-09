import Banner from 'components/Common/Banner/Banner';
import MissingDogList from 'components/Report/MissingDog/MissingDogList';
import PersonalDogList from 'components/Report/PersonalDog/PersonalDogList';
import React from 'react';
import { useState } from 'react';
import styles from './Report.module.scss';
import Button from '@mui/material/Button';
import { getBreedsList } from 'apis/api/shelter';
import { useQuery } from '@tanstack/react-query';
import Select from 'react-select';
import Navbar from 'components/ShelterPage/Navbar/Navbar';
import MenuLink from 'components/ShelterPage/Navbar/MenuLink';
import { useEffect } from 'react';

export default function Report() {
  const [selected, setSelected] = useState('실종');
  const handleClick = value => {
    setSelected(value);
  };

  return (
    <div>
      <Banner title="실종견 / 임보견" />
      <div className={styles.container}>
        {/* <button
          onClick={() => handleClick('실종')}
          className={selected === '실종' ? styles['button-selected'] : styles['button-unselected']}
        > */}
        <button
          variant={selected === '실종' ? 'contained' : 'outlined'}
          onClick={() => handleClick('실종')}
          className={selected === '실종' ? styles['button-active'] : styles['button']}
        >
          실종견
        </button>
        {/* </button> */}
        <button
          variant={selected === '임보' ? 'contained' : 'outlined'}
          onClick={() => handleClick('임보')}
          className={selected === '임보' ? styles['button-active'] : styles['button']}
        >
          임보견
        </button>
      </div>

      <hr />
      <div>
        {selected === '실종' ? <MissingDogList /> : null}
        {selected === '임보' ? <PersonalDogList /> : null}
      </div>
    </div>
  );
}
