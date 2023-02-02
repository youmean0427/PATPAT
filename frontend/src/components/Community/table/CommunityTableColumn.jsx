import React from 'react';
import styles from './CommunityTable.module.scss';

const CommunityTableColumn = ({ children }) => {
  return <td className={styles['community-table-column']}>{children}</td>;
};

export default CommunityTableColumn;
