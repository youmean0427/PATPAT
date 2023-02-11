import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React, { useState } from 'react';
import styles from './BoardsList.module.scss';
import BoardsItem from '../Items/BoardsItem';
import { MdArrowForwardIos, MdArrowBackIosNew } from 'react-icons/md';

export default function Boards() {
  const [category, setCategory] = useState([true, false, false]);
  const [typeCode, setTypeCode] = useState(0);
  const [page, setPage] = useState(1);
  const LIMIT = 10;

  const { data, isLoading } = useQuery({
    queryKey: ['myBoardList', typeCode],
    queryFn: () => getBoardListByMe(LIMIT, page - 1, typeCode),
  });

  const handleClickPrev = () => {
    setPage(prev => prev - 1);
  };

  const handleClickNext = () => {
    setPage(prev => prev + 1);
  };

  if (isLoading) return;

  return (
    <div className={styles['board-list']}>
      <div className={styles.category}>
        <ul>
          <li
            className={category[0] ? styles.click : styles.default}
            onClick={() => {
              setCategory([true, false, false]);
              setTypeCode(0);
            }}
          >
            입양후기
          </li>
          <li
            className={category[1] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, true, false]);
              setTypeCode(1);
            }}
          >
            무료나눔
          </li>
          <li
            className={category[2] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, false, true]);
              setTypeCode(2);
            }}
          >
            정보공유
          </li>
        </ul>
      </div>
      <br />
      <div className={styles.list}>
        {data.totalCount === 0 ? (
          <div className={styles['no-data']}>등록된 게시물이 없습니다.</div>
        ) : (
          data.list.map(item => <BoardsItem key={item.spDogId} item={item} />)
        )}
      </div>
      {data.totalCount === 0 ? null : (
        <div className={styles.pagination}>
          <button
            onClick={handleClickPrev}
            className={page === 1 ? `${styles.button} ${styles.disabled}` : styles.button}
            disabled={page === 1 ? true : false}
          >
            <MdArrowBackIosNew />
          </button>
          <span>{page}</span>
          <button
            onClick={handleClickNext}
            className={
              data.totalPage === 0 || page === data.totalPage ? `${styles.button} ${styles.disabled}` : styles.button
            }
            disabled={data.totalPage === 0 || page === data.totalPage ? true : false}
          >
            <MdArrowForwardIos />
          </button>
        </div>
      )}
    </div>
  );
}
