import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React from 'react';
import BoardsItem from '../Items/BoardsItem';
import Table from 'components/Common/Table';

export default function ShareList({ code }) {
  const { data, isLoading } = useQuery({
    queryKey: ['myBoardList'],
    queryFn: () => getBoardListByMe(20, 0, code),
  });

  if (isLoading) return;
  console.log(code, data);
  return (
    <Table>
      {data.map(item => (
        <BoardsItem key={item.boardId} item={item} />
      ))}
    </Table>
  );
}
