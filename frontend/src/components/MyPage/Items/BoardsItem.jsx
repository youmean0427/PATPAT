import React from 'react';
import styles from './BoardsItem.module.scss';

export default function ReviewItem({ item }) {
  const { title, author, registDate, count, typeCode } = item;

  return (
    <tr className={styles.items}>
      <td className={styles.type}>
        {item.typeCode === 0 ? '[입양후기]' : item.typeCode === 1 ? '[무료나눔]' : '[정보공유]'}
      </td>
      <td className={styles.title}>{item.title}</td>
      <td className={styles.author}>{item.author}</td>
      <td className={styles.count}>{item.count}</td>
      <td className={styles.registDate}>
        {item.registDate[0]}.{item.registDate[1]}.{item.registDate[2]}
      </td>
    </tr>
  );
}
