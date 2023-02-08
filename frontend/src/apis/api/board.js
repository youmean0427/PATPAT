import { defaultInstance, authInstance } from 'apis/utils';

// GET APIs

/**
 * GET : 게시판 리스트 데이터를 가져온다.
 * @param {int} typeCode 0 : 입양후기 , 1 : 무료 용품 나눔 , 2 : 정보 공유
 * @param {int} limit 리스트로 가져올 item 개수
 * @param {int} offset skip할 개수
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const getBoardList = async (typeCode, limit, offset) => {
  const { data } = await defaultInstance.get(`/boards?typeCode=${typeCode}&limit=${limit}&offSet=${offset}`);
  return data;
};

/**
 * GET : 내가 작성한 게시판 리스트 데이터를 가져온다.
 * @param {int} typeCode null : 전체, 0 : 입양후기, 1 : 무료 용품 나눔 , 2 : 정보 공유
 * @param {int} limit 리스트로 가져올 item 개수
 * @param {int} offset skip할 개수
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const getBoardListByMe = async (limit, offset, typecode) => {
  const { data } = await authInstance.get(`/boards/me?limit=${limit}&offSet=${offset}&typecode=${typecode}`);
  return data;
};

/**
 * GET : 게시판 글의 상세 데이터를 가져온다.
 * @param {int} boardId 게시판 글의 id
 * @returns 게시판 글 중 id에 해당하는 item
 */
export const getBoardDetail = async boardId => {
  const { data } = await defaultInstance.get(`/boards/${boardId}`);
  return data;
};

// Post APIs

/**
 * POST : 게시판 글 생성
 * @param {image : [file],title : string, content : string, typeCode : int} formData typeCode는 동일 (입양후기,무료 나눔,정보 공유)
 * @returns 성공 , 실패 여부
 */
export const CreateBoard = async formData => {
  const res = await authInstance.post('/boards', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};

/**
 * POST : 게시판 글 수정
 * @param {int} boardId 수정할 게시판의 id
 * @param {image : [file],title : string, content : string, typeCode : int} formData typeCode는 동일 (입양후기,무료 나눔,정보 공유)
 * @returns 성공 , 실패 여부
 */
export const UpdateBoard = async (boardId, formData) => {
  const res = await authInstance.post(`/boards/${boardId}`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};

/**
 * POST : 게시글에 댓글을 생성
 * @param {json} data
 * {
    "author": "작성자",
    "boardId": 0,
    "commentId": 0,
    "content": "본문",
    "regDt": "2023-01-29T13:57:03.199Z",
    "replyList": "대댓글 리스트"
  }
 * @returns 성공 , 실패 여부
 */
export const CreateComment = async data => {
  const res = await authInstance.post('/boards/comments', data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

/**
 * POST : 대댓글 생성
 * @param {json} data
 *  {
  "author": "작성자",
  "commentId": 0,
  "content": "본문",
  "regDt": "2023-01-29T14:35:25.076Z",
  "replyId": 0
}
 * @returns 성공 , 실패 여부
 */
export const CreateReply = async data => {
  const res = await authInstance.post('/boards/comments/replies', data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

// PUT APIs

/**
 * PUT : 게시글 댓글 수정
 * @param {int} commentId 댓글 id
 * @param {json} data
 * {
    "content": "본문",
  }
 * @returns 성공 , 실패 여부
 */
export const UpdateComment = async (commentId, data) => {
  const res = await authInstance.put(`/boards/comments/${commentId}`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

/**
 * PUT : 게시글 대댓글 수정
 * @param {int} replyId
 * @param {json} data
 * {
  "content": "본문",
}
 */
export const UpdateReply = async (replyId, data) => {
  const res = await authInstance.put(`/boards/comments/replies/${replyId}`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

// DELETE APIs

/**
 * DELETE : 게시판 글 삭제
 * @param {int} boardId 삭제할 게시판의 id
 * @returns 성공 , 실패 여부
 */
export const DeleteBoard = async boardId => {
  const res = await authInstance.delete(`/boards/${boardId}`);
  return res;
};

/**
 * DELETE : 게시글 댓글 삭제
 * @param {int} commentId 댓글 id
 * @returns 성공 , 실패 여부
 */
export const DeleteComment = async commentId => {
  const res = await authInstance.delete(`/boards/comments/${commentId}`);
  return res;
};

/**
 * DELETE : 게시판 대댓글 삭제
 * @param {int} replyId 대댓글 id
 * @returns 성공 , 실패 여부
 */
export const DeleteReply = async replyId => {
  const res = await authInstance.delete(`/boards/comments/replies/${replyId}`);
  return res;
};
