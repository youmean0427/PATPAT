import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React, { useState } from 'react';
import styles from './BoardItem.module.scss';
import Table from 'components/Common/Table';
import Pagination from 'react-js-pagination';
import './Paging.css';

export default function BoardList({ typeCode, count }) {
  const [page, setPage] = useState(1);
  const LIMIT = 10;

  const handlePageChange = page => {
    setPage(page);
  };

  const { data, isLoading } = useQuery({
    queryKey: ['getBoardListByMe', typeCode],
    queryFn: () => {
      return getBoardListByMe(LIMIT, page - 1, typeCode);
    },
  });

  if (isLoading) return;

  return (
    <div>
      <Table>
        {data.list.map(item => (
          <tr key={item.boardId} className={styles['table-item']}>
            <td className={styles.type}>[{item.type}]</td>
            <td className={styles.title}>{item.title}</td>
            <td className={styles.author}>{item.author}</td>
            <td className={styles.count}>{item.count}</td>
            <td className={styles.date}>
              {item.registDate[0] +
                '.' +
                (item.registDate[1] <= 9 ? '0' + item.registDate[1] : item.registDate[1]) +
                '.' +
                (item.registDate[2] <= 9 ? '0' + item.registDate[2] : item.registDate[2])}
            </td>
          </tr>
        ))}
      </Table>
      <Pagination
        activePage={page}
        itemsCountPerPage={LIMIT}
        totalItemsCount={count}
        pageRangeDisplayed={5}
        prevPageText={'‹'}
        nextPageText={'›'}
        onChange={handlePageChange}
      />
    </div>
  );
}
