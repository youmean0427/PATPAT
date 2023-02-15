import { useQuery } from '@tanstack/react-query';
import { getShelterConsultationsTime } from 'apis/api/consulting';
import moment from 'moment';
import React, { useState } from 'react';
import styles from './SelectTime.module.scss';

export default function SelectTime({ shelterId, value, saveFunction, click, setClick }) {
  const { data, isLoading } = useQuery({
    queryKey: ['getShelterConsultationsTime', moment(value).format('YYYY-MM-DD')],
    queryFn: () => getShelterConsultationsTime(shelterId, moment(value).format('YYYY-MM-DD')),
  });

  if (isLoading) return;
  console.log(data);
  return (
    <div className={styles['consulting-time']}>
      <div className={styles.morning}>
        <p>오전</p>
        <div className={styles.times}>
          <div
            onClick={() => {
              saveFunction(data[0].timeCode);
              setClick([true, false, false, false, false, false]);
            }}
            className={click[0] ? styles.click : data[0].active ? styles.activate : styles.deactivate}
          >
            10:00
          </div>
          <div
            onClick={() => {
              saveFunction(data[1].timeCode);
              setClick([false, true, false, false, false, false]);
            }}
            className={click[1] ? styles.click : data[1].active ? styles.activate : styles.deactivate}
          >
            11:00
          </div>
        </div>
      </div>
      <div className={styles.afternoon}>
        <p>오후</p>
        <div className={styles.times}>
          <div
            onClick={() => {
              saveFunction(data[2].timeCode);
              setClick([false, false, true, false, false, false]);
            }}
            className={click[2] ? styles.click : data[2].active ? styles.activate : styles.deactivate}
          >
            14:00
          </div>
          <div
            onClick={() => {
              saveFunction(data[3].timeCode);
              setClick([false, false, false, true, false, false]);
            }}
            className={click[3] ? styles.click : data[3].active ? styles.activate : styles.deactivate}
          >
            15:00
          </div>
          <div
            onClick={() => {
              saveFunction(data[4].timeCode);
              setClick([false, false, false, false, true, false]);
            }}
            className={click[4] ? styles.click : data[4].active ? styles.activate : styles.deactivate}
          >
            16:00
          </div>
          <div
            onClick={() => {
              saveFunction(data[5].timeCode);
              setClick([false, false, false, false, false, true]);
            }}
            className={click[5] ? styles.click : data[5].active ? styles.activate : styles.deactivate}
          >
            17:00
          </div>
        </div>
      </div>
    </div>
  );
}
