import React from 'react';
import styles from './BoardsItem.module.scss';

export default function ReviewItem({ item }) {
  const { title, author, registDate, count, typeCode } = item;

  return (
    <tr className={styles.items}>
      <td className={styles.type}>{typeCode === 0 ? '[입양후기]' : typeCode === 1 ? '[무료나눔]' : '[정보공유]'}</td>
      <td className={styles.title}>{title}</td>
      <td className={styles.author}>{author}</td>
      <td className={styles.count}>{count}</td>
      <td className={styles.registDate}>
        {registDate[0]}.{registDate[1] <= 9 ? '0' + registDate[1] : registDate[1]}.
        {registDate[2] <= 9 ? '0' + registDate[2] : registDate[2]}
      </td>
    </tr>
  );
}
