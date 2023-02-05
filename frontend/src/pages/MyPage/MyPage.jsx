import { useQuery } from '@tanstack/react-query';
import { getUserInfo } from 'apis/api/user';
import React from 'react';
import styles from './MyPage.module.scss';
import ForPawMeter from 'components/MyPage/ForPawMeter';
import Contents from 'components/MyPage/Contents';
export default function MyPage() {
  const { data, isLoading } = useQuery({
    queryKey: ['getUserInfo'],
    queryFn: () => getUserInfo(),
  });

  if (isLoading) return;

  return (
    <div className={styles.container}>
      <ForPawMeter data={data} />
      <hr className={styles.line} />
      <div className={styles.contents}>
        <Contents data={data} />
      </div>
    </div>
  );
}
