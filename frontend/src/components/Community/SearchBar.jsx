import { useState } from 'react';
import styles from './SearchBar.module.scss';
import SearchIcon from '@mui/icons-material/Search';

const SearchBar = () => {
  const [keyword, setKeyword] = useState('');

  const onSearch = event => {
    event.preventDefault();
    setKeyword(event.target.value);
  };
  const onSubmit = event => {
    event.preventDefault();
    if (keyword === '') {
      return;
    }
  };

  return (
    <div className={styles['searchbar']}>
      <form onSubmit={onSubmit}>
        <div className={styles.searchBar}>
          <input type="text" placeholder="Search" onSearch={onSearch} value={keyword}></input>
          <SearchIcon type="btn" className={styles['btn']}>
            돋보기 모양 이미지
          </SearchIcon>
        </div>
      </form>
    </div>
  );
};

export default SearchBar;
