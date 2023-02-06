import ReportCreateContent from 'components/Report/Create/ReportCreateContent';
import React from 'react';
import { useLocation } from 'react-router';
import styles from './ReportCreate.module.scss';
export default function ReportCreate() {
  // Create Default Value
  // const items = {
  //   breedId: 0,
  //   title: '',
  //   name: '',
  //   isNeutered: 0,
  //   fileUrlList: [],
  //   genderCode: 0,
  //   kg: 0,
  //   latitude: 0,
  //   longitude: 0,
  //   content: '',
  //   categoryCloth: 0,
  //   categoryClothColor: 0,
  //   categoryColor: 0,
  //   categoryEar: 0,
  //   categoryPattern: 0,
  //   categoryTail: 0,
  // };
  // const state = 0;
  return (
    <div>
      <div className={styles.title}>실종견/임보견 신고</div>
      <hr />
      <ReportCreateContent />
    </div>
  );
}
