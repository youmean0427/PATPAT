import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState, useEffect } from 'react';
import styles from './FreeShare.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import NoData from 'components/Common/NoData';
import { useNavigate } from 'react-router-dom';
import CommunityTableItem from 'components/Community/items/CommunityTableItem';

export default function FreeShare() {
  const navigate = useNavigate();
  const [page, setPage] = useState(10);

  const { data, isLoading } = useQuery({
    queryKey: ['freeShareList', page],
    queryFn: () => getBoardList(2, 10, 0),
  });

  if (isLoading) return;

  return (
    <ShelterContainer title="무료 나눔">
      <div className={styles.write}>
        <div onClick={() => navigate('/community/regist', { state: { stateCode: 2 } })}>글쓰기</div>
      </div>
      {data.totalCount === 0 ? (
        <NoData>등록된 무료 나눔 게시물이 없습니다.</NoData>
      ) : (
        <div className={styles.list}>
          <CommunityTableItem key={data.boardId} typeCode={2} item={data} />;
        </div>
      )}
    </ShelterContainer>
  );
}
