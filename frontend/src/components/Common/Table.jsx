import React from 'react';
import styles from './Table.module.scss';
export default function Table({ children }) {
  return (
    <div className={styles.table}>
      <table>
        <thead>
          <tr>
            <th className={styles.cat01}>분류</th>
            <th className={styles.cat02}>제목</th>
            <th className={styles.cat03}>작성자</th>
            <th className={styles.cat04}>조회수</th>
            <th className={styles.cat05}>등록일</th>
          </tr>
        </thead>
        <tbody>{children}</tbody>
      </table>
    </div>
  );
}
