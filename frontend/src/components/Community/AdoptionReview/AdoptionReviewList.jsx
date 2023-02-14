import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState, useEffect } from 'react';
import AdoptionReviewItem from './AdoptionReviewItem';
import styles from './AdoptionReviewList.module.scss';
import { Link } from 'react-router-dom';
import Banner from 'components/Common/Banner/Banner';

export default function AdoptionReviewList() {
  const [page, setPage] = useState(10);
  const [hasMore, setHasMore] = useState(true);
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['adoptionList'],
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

  if (isLoading) return <div>Loading...</div>;

  return (
    <>
      <div className={styles['container']}>
        <Banner title="입양 후기" />
        <div className={styles['list']}>
          {data.list.map((item, index) => (
            <AdoptionReviewItem key={item.id} item={item} />
          ))}
        </div>
        {hasMore ? (
          () => {
            alert('모든 데이터가 로딩 되었습니다.');
          }
        ) : (
          <div>has more</div>
        )}
      </div>
      <div>
        <Link to="/community/infowrite">
          <button>글쓰기</button>
        </Link>
      </div>
    </>
  );
}
