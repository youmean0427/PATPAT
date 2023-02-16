import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState, useEffect } from 'react';
import styles from './Information.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import NoData from 'components/Common/NoData';
import { useNavigate } from 'react-router-dom';
import CommunityTableItem from 'components/Community/items/CommunityTableItem';

export default function Information() {
  const navigate = useNavigate();
  const [page, setPage] = useState(10);

  const { data, isLoading } = useQuery({
    queryKey: ['freeShareList', page],
    queryFn: () => getBoardList(1, 10, 0),
  });

  if (isLoading) return;

  return (
    <ShelterContainer title="정보 공유">
      <div className={styles.write}>
        <div onClick={() => navigate('/community/regist', { state: { stateCode: 1 } })}>글쓰기</div>
      </div>
      {data.totalCount === 0 ? (
        <NoData>등록된 정보 공유 게시물이 없습니다.</NoData>
      ) : (
        <div className={styles.list}>
          <CommunityTableItem key={data.boardId} typeCode={1} item={data} />
        </div>
      )}
    </ShelterContainer>
  );
}
