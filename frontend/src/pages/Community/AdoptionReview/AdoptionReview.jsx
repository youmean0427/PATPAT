import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState, useEffect } from 'react';
import styles from './AdoptionReview.module.scss';
import ShelterContainer from 'containers/ShelterContainer';
import NoData from 'components/Common/NoData';
import AdoptionReviewItem from 'components/Community/items/AdoptionReviewItem';
import { useNavigate } from 'react-router-dom';

export default function AdoptionReview() {
  const navigate = useNavigate();
  const [page, setPage] = useState(10);
  const [hasMore, setHasMore] = useState(true);
  const [isChange, setIsChange] = useState(false);
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['adoptionList', isChange],
    queryFn: () => getBoardList(0, page, 0),
    config: {
      onSuccess: (prevData, newData) => {
        if (newData.list.length === 0) {
          setHasMore(false);
        }
        return [...prevData.list, ...newData.list];
      },
    },
  });

  useEffect(() => {
    const handleScroll = () => {
      const { innerHeight, scrollY, document } = window;
      if (document.body.offsetHeight - (innerHeight + scrollY) < 100 && hasMore) {
        setPage(page + 1);
        refetch();
      }
    };

    const timer = setInterval(() => {
      window.addEventListener('scroll', handleScroll);
    }, 10);
    return () => {
      clearInterval(timer);
      window.removeEventListener('scroll', handleScroll);
    };
  }, [page, hasMore]);

  if (isLoading) return;

  return (
    <ShelterContainer title="입양 후기">
      <div className={styles.write}>
        <div onClick={() => navigate('/community/regist', { state: { stateCode: 0 } })}>글쓰기</div>
      </div>
      {data.totalCount === 0 ? (
        <NoData>등록된 입양후기 게시물이 없습니다.</NoData>
      ) : (
        <div className={styles.list}>
          {data.list.map(item => {
            return <AdoptionReviewItem key={item.boardId} item={item} change={setIsChange} />;
          })}
        </div>
      )}
    </ShelterContainer>
  );
}
