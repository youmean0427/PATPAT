import React from 'react';
import styles from './Table.module.scss';
export default function Table({ children }) {
  return (
    <div className={styles.table}>
      <table>
        <thead>
          <tr>
            <th>분류</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>등록일</th>
          </tr>
        </thead>
        <tbody>{children}</tbody>
      </table>
    </div>
  );
}
