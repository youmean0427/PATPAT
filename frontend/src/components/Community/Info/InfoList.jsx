import React, { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import { Link } from 'react-router-dom';
import CommunityTable from '../table/CommunityTable';
import CommunityTableColumn from '../table/CommunityTableColumn';
import CommunityTableRow from '../table/CommunityTableRow';
import SearchBar from '../SearchBar';

export default function InfoList() {
  const limit = 15;
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;
  const { isLoading, data } = useQuery({
    queryKey: ['adoptionList'],
    queryFn: () => getBoardList(0, 4, 0),
  });
  if (isLoading) return; // isLoading = false, data = 데이터 리스트(입양 후기

  return (
    <>
      <SearchBar />
      <CommunityTable headersName={['제목', '작성자', '조회수', '등록일']}>
        {data.slice(offset, offset + limit).map((item, index) => {
          return (
            <CommunityTableRow key={index}>
              <CommunityTableColumn>
                <Link to={`/community/infodetail/${item.id}`} state={{ item: item }}>
                  {item.title}
                </Link>
              </CommunityTableColumn>
              <CommunityTableColumn>{item.author}</CommunityTableColumn>
              <CommunityTableColumn>{item.count}</CommunityTableColumn>
              <CommunityTableColumn>{item.registDate}</CommunityTableColumn>
            </CommunityTableRow>
          );
        })}
      </CommunityTable>
      <footer></footer>
    </>
  );
}
