import { useQuery } from '@tanstack/react-query';
import { getMissingList } from 'apis/api/report';
import React from 'react';
import MissingDogItem from './MissingDogItem';
import styles from './MissingDogList.module.scss';
export default function MissingDogList() {
  const { isLoading, data } = useQuery({
    queryKey: ['missingDogList'],
    queryFn: () => getMissingList(0, 6, 0),
  });
  const [num, setnum] = React.useState([]);
  const [search, setSearch] = React.useState('');

  function filtered(e) {
    setSearch(e.target.value);
    setnum(
      data.filter(itemList => {
        return itemList.title.includes(search);
      })
    );
  }
  console.log(num, search);
  if (isLoading) return;

  return (
    <div>
      <div>
        <input type="text" value={search} onChange={filtered} />
      </div>
      <div className={styles.container}>
        <div className={styles.list}>
          {search === ''
            ? data.map(item => <MissingDogItem key={item.missingId} item={item} />)
            : num.map(item => <MissingDogItem key={item.missingId} item={item} />)}
        </div>
      </div>
    </div>
  );
}
