import React, { useState } from 'react';
import { CreateComment, getBoardList } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';
import styles from './Comment.module.scss';

export default function CommentWrite(props) {
  const [content, setContent] = useState('');
  const boardId = props.boardId;

  let formData2 = { boardId: boardId, content: content };

  const { mutate: mutation } = useMutation(['commentList'], () => {
    return CreateComment(formData2);
  });

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
        }}
      >
        <div>
          <div className={styles['input-row']}>
            <input
              className={styles['inputfield']}
              type="textfiled"
              placeholder="내용을 입력하세요"
              onChange={e => setContent(e.target.value)}
            />
            <button className={styles['input-btn']} type="submit">
              댓글등록
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}
