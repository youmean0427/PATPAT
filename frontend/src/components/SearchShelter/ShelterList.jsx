import { useQuery } from '@tanstack/react-query';
import { getShelterList } from 'apis/api/shelter';
import React from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { searchShelterPageState, selectBreedState, selectGugunState, selectSidoState } from 'recoil/atoms/shelter';
import ShelterItem from './ShelterItem';
import styles from './ShelterList.module.scss';
import Pagination from 'react-js-pagination';
import Loading from 'components/Common/Loading';
function ShelterList() {
  const sido = useRecoilValue(selectSidoState);
  const gugun = useRecoilValue(selectGugunState);
  const breed = useRecoilValue(selectBreedState);
  const [page, setPage] = useRecoilState(searchShelterPageState);
  const LIMIT = 10;
  const { data, isLoading, isFetching } = useQuery(
    ['shelterList', sido.sidoCode, gugun.gugunCode, breed.breedId, page],
    () => {
      return getShelterList(sido.sidoCode, gugun.gugunCode, breed.breedId, LIMIT, page - 1);
    },
    { staleTime: 1000 * 60 * 5 }
  );
  const handlePageChange = pageNumber => {
    setPage(pageNumber);
  };
  if (isLoading || isFetching) return <Loading></Loading>;
  return (
    <div className={styles.container}>
      {data.totalCount !== 0 ? (
        data.list.map((item, index) => {
          return <ShelterItem key={index} item={item} />;
        })
      ) : (
        <span>검색 결과가 없습니다.</span>
      )}
      <Pagination
        activePage={page}
        itemsCountPerPage={10}
        totalItemsCount={data.totalCount}
        pageRangeDisplayed={5}
        onChange={handlePageChange}
      />
    </div>
  );
}

export default React.memo(ShelterList);
