import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import React, { useState, useEffect } from 'react';
import AdoptionReviewItem from './AdoptionReviewItem';
import styles from './AdoptionReviewList.module.scss';
import { Link } from 'react-router-dom';
import Banner from 'components/Common/Banner/Banner';

export default function AdoptionReviewList() {
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const { data, isLoading } = useQuery({
    queryKey: ['adoptionList', page],
    queryFn: () => getBoardList(page, 200, 0),
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
      }
    };

    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, [page, hasMore]);

  if (isLoading) return <div>Loading...</div>;

  return (
    <div className={styles.list}>
      <Banner />
      {data.list.map(item => (
        <AdoptionReviewItem key={item.id} item={item} />
      ))}
      {hasMore ? <div>Loading more...</div> : <div>All data has been loaded</div>}
      <Link to="/community/infowrite">
        <button>글쓰기</button>
      </Link>
    </div>
  );
}
