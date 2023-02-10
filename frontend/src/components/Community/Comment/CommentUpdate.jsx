// import React, { useState } from 'react';
// import { UpdateComment } from 'apis/api/board';
// import { useMutation } from '@tanstack/react-query';
// import { useLocation, useNavigate } from 'react-router-dom';

// export default function CommentUpdate(props) {
//   const navigate = useNavigate();
//   const location = useLocation();
//   const data = location.state.item;

//   const author = '최창근';
//   const [content, setContent] = useState('');
//   const comment = props;
//   const userId = 1;

//   //  ========== 사진 업로드 ==========
//   // 이미지 상대경로 저장

//   let formData2 = new FormData();
//   formData2.append('author', author);
//   formData2.append('boardId', boardId);
//   formData2.append('content', content);
//   formData2.append('userId', userId);

//   const { mutate: mutation } = useMutation(['boardReviewList'], () => {
//     return UpdateComment(commentId, formData2);
//   });

//   const onClickBtn = () => {
//     navigate('/community/info');
//   };

//   return (
//     <div>
//       <form
//         onSubmit={e => {
//           e.preventDefault();
//           mutation();
//         }}
//       >
//         <div>
//           <div>
//             <input type="textfiled" placeholder="내용을 입력하세요" onChange={e => setContent(e.target.value)} />
//           </div>
//           <button type="submit">댓글등록</button>
//         </div>
//       </form>
//     </div>
//   );
// }
