import React, { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getBoardList } from 'apis/api/board';
import { Link } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Pagination } from '@mui/material';
import styles from '../List.module.scss';
import Banner from 'components/Common/Banner/Banner';

export default function InfoList() {
  const [page, setPage] = useState(1);
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['infoList'],
    queryFn: () => getBoardList(2, 200, 0),
  });
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredData, setFilteredData] = useState(data);
  if (isLoading) return;
  let newData = data.list;
  const PER_PAGE = 10;
  const count = Math.ceil(newData.length / PER_PAGE);
  const handleChange = (e, v) => {
    setPage(v);
  };
  const handleSearch = event => {
    setSearchTerm(event.target.value);
  };
  const handleSubmit = event => {
    event.preventDefault();
    setFilteredData(newData.filter(row => row.title.toLowerCase().includes(searchTerm.toLowerCase())));
  };

  return (
    <div className={styles['list-container']}>
      <Banner />
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Search by title" value={searchTerm} onChange={handleSearch} />
      </form>
      <button type="submit">Search</button>
      <TableContainer className={styles['list-table']} component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow className={styles['list-table-row']}>
              <TableCell className={styles['list-table-cell']} align="center">
                제목
              </TableCell>
              <TableCell className={styles['list-table-cell']} align="center">
                작성자
              </TableCell>
              <TableCell className={styles['list-table-cell']} align="center">
                조회수
              </TableCell>
              <TableCell className={styles['list-table-cell']} align="center">
                등록일
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {newData.slice((page - 1) * PER_PAGE, page * PER_PAGE).map((item, index) => (
              <TableRow
                component={Link}
                to={`/community/infodetail/${item.boardId}`}
                key={index}
                className={styles['table-row']}
                state={{ item: item }}
              >
                <TableCell className={styles['table-cell']} align="center">
                  {item.title}
                </TableCell>
                <TableCell className={styles['table-cell']} align="center">
                  {item.author}
                </TableCell>
                <TableCell className={styles['table-cell']} align="center">
                  {item.count}
                </TableCell>
                <TableCell className={styles['table-cell']} align="center">
                  {item.registDate}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Pagination
        count={count}
        sx={{ display: 'flex', justifyContent: 'center', padding: '15px 0' }}
        size="large"
        page={page}
        variant="outlined"
        shape="rounded"
        onChange={handleChange}
      />
      <Link to="/community/infowrite">
        <button>글쓰기</button>
      </Link>
    </div>
  );
}
