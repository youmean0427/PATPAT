import React from 'react';
import styles from './CommunityTable.module.scss';

const CommunityTable = props => {
  const { headersName, children } = props;

  return (
    <table className={styles['community-table']}>
      <thead>
        <tr>
          {headersName.map((item, index) => {
            return (
              <td className={styles['community-table-header-column']} key={index}>
                {item}
              </td>
            );
          })}
        </tr>
      </thead>
      <tbody>{children}</tbody>
    </table>
  );
};

export default CommunityTable;
