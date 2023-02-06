import ReportCreateContent from 'components/Report/Create/ReportCreateContent';
import React from 'react';
import styles from './ReportCreate.module.scss';
export default function ReportCreate() {
  return (
    <div>
      <div className={styles.title}>실종견/임보견 신고</div>
      <hr />
      <ReportCreateContent />
    </div>
  );
}
