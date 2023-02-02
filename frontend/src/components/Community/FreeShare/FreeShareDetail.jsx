import React from 'react';
import { useLocation } from 'react-router-dom';
import styles from '../Detail.module.scss';

export default function FreeShareDetail() {
  const location = useLocation();
  const data = location.state.item;

  return (
    <div className={styles['detail-view-wrapper']}>
      {data ? (
        <>
          <div className={styles['detail-title']}>
            <label>{data.title}</label>
          </div>
          <div className={styles['sub-title']}>
            <div className={styles['detail-view-row']}>
              <div>{data.author}</div>
            </div>
            <div className={styles['detail-view-row']}>
              <label>{data.count}</label>
            </div>
            <div className={styles['detail-view-row']}>
              <label>{data.registDate}</label>
            </div>
            <div className={styles['detail-btn']}>
              <button onClick={() => console.log('눌렷다 눌렷어')}>수정</button>
            </div>
            <div className={styles['detail-btn']}>
              <button>삭제</button>
            </div>
          </div>
          <div className={styles['detail-img']}>
            <img src={data.thumbnail} alt="thumbnail" />
          </div>
          <div className={styles['detail-view-content']}>
            <div>{data.content}</div>
          </div>
        </>
      ) : (
        '해당 게시글을 찾을 수 없습니다.'
      )}
      <a href="javascript:history.back()">목록으로 돌아가기</a>
    </div>
  );
}
