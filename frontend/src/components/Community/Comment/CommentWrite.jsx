import React, { useState } from 'react';
import { CreateComment } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';

export default function CommentWrite(props) {
  const [content, setContent] = useState('');
  const boardId = props.boardId;

  let formData2 = { boardId: boardId, content: content };
  // formData2.append('author', author);
  // formData2.append('boardId', boardId);
  // formData2.append('content', content);
  // formData2.append('userId', userId);

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
          <div>
            <input type="textfiled" placeholder="내용을 입력하세요" onChange={e => setContent(e.target.value)} />
          </div>
          <button type="submit">댓글등록</button>
        </div>
      </form>
    </div>
  );
}
