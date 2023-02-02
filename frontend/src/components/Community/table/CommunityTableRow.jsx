import React from 'react';
import styles from './CommunityTable.module.scss';

const CommunityTableRow = ({ children }) => {
  return <tr className={styles['community-table-row']}>{children}</tr>;
};

export default CommunityTableRow;
