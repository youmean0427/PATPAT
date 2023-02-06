import ReportCreateContent from 'components/Report/Create/ReportCreateContent';
import React from 'react';
import styles from './ReportCreate.module.scss';
export default function ReportCreate() {
  const items = {
    breedName: 0,
    categoryCloth: 0,
    categoryClothColor: 0,
    categoryColor: 0,
    categoryEar: 0,
    categoryPattern: 0,
    categoryTail: 0,
    neutered: 0,
    fileUrlList: [],
    gender: 0,

    latitude: '위도',
    longitude: '경도',

    missingId: 1,
    personalProtectionId: 0,
    userId: 0,
  };
  const state = 0;
  return (
    <div>
      <div className={styles.title}>실종견/임보견 신고</div>
      <hr />
      <ReportCreateContent items={items} nowState={state} />
    </div>
  );
}
