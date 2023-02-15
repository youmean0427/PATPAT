import { useQuery } from '@tanstack/react-query';
import { getBoardListByMe } from 'apis/api/board';
import React from 'react';
import BoardItem from '../Items/BoardItem';
import NoData from 'components/Common/NoData';

export default function ConsultingList({ typeCode }) {
  const { data, isLoading } = useQuery({
    queryKey: ['getMyConsultations', typeCode],
    queryFn: () => {
      return getBoardListByMe(10, 0, typeCode);
    },
  });

  if (isLoading) return;
  return (
    <div>
      {data.totalCount === 0 ? (
        typeCode === 0 ? (
          <NoData>등록된 입양후기 게시물이 없습니다.</NoData>
        ) : typeCode === 1 ? (
          <NoData>등록된 정보공유 게시물이 없습니다.</NoData>
        ) : (
          <NoData>등록된 무료나눔 게시물이 없습니다.</NoData>
        )
      ) : (
        <BoardItem typeCode={typeCode} count={data.totalCount} />
      )}
    </div>
  );
}
