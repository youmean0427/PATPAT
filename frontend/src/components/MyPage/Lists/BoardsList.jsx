import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React, { useState } from 'react';
import styles from './BoardsList.module.scss';
import BoardsItem from '../Items/BoardsItem';

export default function Boards() {
  const [category, setCategory] = useState([true, false, false]);
  const [typeCode, setTypeCode] = useState(0);

  const { data, isLoading } = useQuery({
    queryKey: ['myBoardList', typeCode],
    queryFn: () => getBoardListByMe(20, 0, typeCode),
  });

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
        {data.list.map(item => {
          <BoardsItem key={item.boardId} item={item} />;
        })}
      </div>
    </div>
  );
}
