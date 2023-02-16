import { useQuery } from '@tanstack/react-query';
import { getProtectList } from 'apis/api/protect';
import AbandonedDogItem from 'components/Common/Home/AbandonedDogItem';
import ShelterContainer from 'containers/ShelterContainer';
import React, { useState } from 'react';
import styles from './MoreProduct.module.scss';

export default function MoreProduct() {
  const [page, setPage] = useState(1);
  const { data } = useQuery({
    queryKey: ['protectListSortedByEuthanasia', page],
    queryFn: () => getProtectList(0, 0, 8 * page, 0),
  });
  return (
    <ShelterContainer title="보호 동물 전체 보기">
      <div className={styles.list}>
        {data?.list?.map(item => {
          return <AbandonedDogItem key={item.protectId} item={item} />;
        })}
      </div>
      <button onClick={() => setPage(prev => prev + 1)}>더 보기</button>
    </ShelterContainer>
  );
}
