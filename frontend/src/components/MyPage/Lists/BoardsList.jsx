import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React, { useState } from 'react';
import BoardsItem from '../Items/BoardsItem';
import styles from './BoardsList.module.scss';

export default function BoardsList() {
  const [category, setCategory] = useState([true, false, false, false]);
  const { data, isLoading } = useQuery({
    queryKey: ['myBoardListAll'],
    queryFn: () => getBoardListByMe(0, 3, 0),
  });
  if (isLoading) return;

  return (
    <>
      <div className={styles.category}>
        <ul>
          <li
            className={category[0] ? styles.click : styles.default}
            onClick={() => setCategory([true, false, false, false])}
          >
            전체
          </li>
          <li
            className={category[1] ? styles.click : styles.default}
            onClick={() => setCategory([false, true, false, false])}
          >
            입양후기
          </li>
          <li
            className={category[2] ? styles.click : styles.default}
            onClick={() => setCategory([false, false, true, false])}
          >
            무료나눔
          </li>
          <li
            className={category[3] ? styles.click : styles.default}
            onClick={() => setCategory([false, false, false, true])}
          >
            정보공유
          </li>
        </ul>
      </div>
      <br />
      <div className={styles.list}>
        {data.map(item => (
          <BoardsItem key={item.id} item={item} />
        ))}
      </div>
    </>
  );
}
