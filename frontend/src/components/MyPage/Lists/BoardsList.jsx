import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React, { useEffect, useState } from 'react';
import BoardsItem from '../Items/BoardsItem';
import styles from './BoardsList.module.scss';
import Table from 'components/Common/Table';
export default function BoardsList() {
  const { data, isLoading } = useQuery({
    queryKey: ['myBoardListAll'],
    queryFn: () => getBoardListByMe(typeCode, 20, 0),
  });

  const [category, setCategory] = useState([true, false, false, false]);
  const [typeCode, setTypeCode] = useState(null);

  if (isLoading) return;

  console.log(data, typeCode);

  return (
    <>
      <div className={styles.category}>
        <ul>
          <li
            className={category[0] ? styles.click : styles.default}
            onClick={() => {
              setCategory([true, false, false, false]);
              setTypeCode(null);
            }}
          >
            전체
          </li>
          <li
            className={category[1] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, true, false, false]);
              setTypeCode(0);
            }}
          >
            입양후기
          </li>
          <li
            className={category[2] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, false, true, false]);
              setTypeCode(1);
            }}
          >
            무료나눔
          </li>
          <li
            className={category[3] ? styles.click : styles.default}
            onClick={() => {
              setCategory([false, false, false, true]);
              setTypeCode(2);
            }}
          >
            정보공유
          </li>
        </ul>
      </div>
      <br />
      <div className={styles.list}>
        <Table>
          {data.map(item => (
            <BoardsItem key={item.boardId} item={item} />
          ))}
        </Table>
      </div>
    </>
  );
}
