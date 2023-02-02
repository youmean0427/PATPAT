import styles from './Pagination.module.scss';

export default function Pagination({ total, limit, page, setPage }) {
  const numPages = Math.ceil(total / limit);

  return (
    <div className={styles['flex-container']}>
      <div className={styles['paginate-ctn']}>
        <button className={styles['round-effect']} onClick={() => setPage(page - 1)} disabled={page === 1}>
          &lt;
        </button>
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <button
              key={i + 1}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? 'page' : null}
              className={styles['round-effect']}
            >
              {i + 1}
            </button>
          ))}
        <button className={styles['round-effect']} onClick={() => setPage(page + 1)} disabled={page === numPages}>
          &gt;
        </button>
      </div>
    </div>
  );
}
