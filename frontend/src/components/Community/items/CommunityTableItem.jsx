import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState } from 'react';
import styles from './CommunityTableItem.module.scss';
import Table from 'components/Common/Table';
import Pagination from 'react-js-pagination';
import './Paging.css';
import DetailModal from 'components/Common/DetailModal';
import BoardDetail from './BoardDetail';

export default function CommunityTableItem({ typeCode, item }) {
  const [modal, setModal] = useState(false);
  const [page, setPage] = useState(1);
  const [boardId, setBoardId] = useState();
  const [title, setTitle] = useState();
  const [isChange, setIsChange] = useState(false);
  const LIMIT = 10;

  const openModal = (idx, tt) => {
    setBoardId(idx);
    setTitle(tt);
    setModal(true);
  };

  const closeModal = () => {
    setIsChange(cur => !cur);
    setModal(false);
  };

  const handlePageChange = page => {
    setPage(page);
  };

  const { data, isLoading } = useQuery({
    queryKey: ['communityTable', isChange, page],
    queryFn: () => {
      return getBoardList(typeCode, LIMIT, page - 1);
    },
  });

  if (isLoading) return;

  return (
    <div>
      <Table>
        {data.list.map(item => (
          <tr key={item.boardId} className={styles['table-item']}>
            <td className={styles.type}>[{item.type}]</td>
            <td
              className={styles.title}
              onClick={() => {
                openModal(item.boardId, item.title);
              }}
            >
              {item.title}
            </td>
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
        totalItemsCount={item.totalCount}
        pageRangeDisplayed={5}
        prevPageText={'‹'}
        nextPageText={'›'}
        onChange={handlePageChange}
      />
      <DetailModal open={modal} close={closeModal} title={title}>
        <BoardDetail boardId={boardId} close={closeModal} change={setIsChange} typeCode={typeCode} />
      </DetailModal>
    </div>
  );
}
