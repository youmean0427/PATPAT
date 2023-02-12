import React from 'react';
import styles from './BoardsItem.module.scss';
import { useNavigate } from 'react-router';
import Table from 'components/Common/Table';

export default function BoardsItem({ item }) {
  const { boardId, title, author, registDate, count, typeCode } = item;
  const navigate = useNavigate();

  return (
    <Table>
      <tr className={styles.items} key={boardId}>
        <td className={styles.type}>{typeCode === 0 ? '[입양후기]' : typeCode === 1 ? '[무료나눔]' : '[정보공유]'}</td>
        <td
          className={styles.title}
          onClick={() =>
            typeCode === 0
              ? navigate(`/community/adoptionreview/${boardId}`)
              : typeCode === 1
              ? navigate(`/community/freeshare/${boardId}`)
              : navigate(`/community/infodetail/${boardId}`)
          }
        >
          {title}
        </td>
        <td className={styles.author}>{author}</td>
        <td className={styles.count}>{count}</td>
        <td className={styles.registDate}>
          {registDate[0]}.{registDate[1] <= 9 ? '0' + registDate[1] : registDate[1]}.
          {registDate[2] <= 9 ? '0' + registDate[2] : registDate[2]}
        </td>
      </tr>
    </Table>
  );
}
