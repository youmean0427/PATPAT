import React, { useState, useEffect } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getBoardDetail } from 'apis/api/board';
import styles from './Comment.module.scss';
import { DeleteComment, UpdateComment, CreateReply, DeleteReply, UpdateReply } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';

export default function CommentList(props) {
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['commentList'],
    queryFn: () => getBoardDetail(props.boardId),
  });

  // 댓글 삭제시 리렌더링
  const onDelete = commentId => {
    DeleteComment(commentId).then(res => {
      refetch();
    });
  };

  // 수정 시 리렌더링
  const onUpdate = (commentId, commentData) => {
    UpdateComment(commentId, commentData).then(res => {
      refetch();
    });
  };

  // 대댓글 생성
  const { mutate: mutation2 } = useMutation(['replyList'], () => {
    console.log(replyForm);
    return CreateReply(replyForm);
  });

  // 대댓글 삭제 시 리렌더링
  const onReplyDelete = replyId => {
    DeleteReply(replyId).then(res => {
      refetch();
      // console.log('refatch 왜 이렇게 되노');
    });
  };

  // 대댓글 수정
  const onReplyUpdate = (replyId, replyForm) => {
    console.log(replyForm);
    UpdateReply(replyId, replyForm).then(res => {
      refetch();
    });
  };

  // 수정 클릭 시 reply content 넣는 input div 보이기
  const [reply, setReply] = useState(true);
  function onClickReplyHide() {
    setReply(reply => !reply);
  }

  const [update, setUpdate] = useState(true);
  function onClickUpdateHide() {
    setUpdate(update => !update);
  }
  const [upUpdate, setUpUpdate] = useState(true);
  function onClickUpUpdateHide() {
    setUpUpdate(upUpdate => !upUpdate);
  }
  const [commentContent, setCommentContent] = useState('');
  const [replyContent, setReplyContent] = useState('');
  let replyId = 0;
  let commentId = 0;
  // const replyAuthor = '조나단';
  //댓글정보{ content: replyContent, commentId: replyCommentId };
  let commentForm = { content: commentContent, commentId: commentId };
  // 대댓글 정보
  let replyForm = { content: replyContent, replyId: replyId };
  // replyForm.append('author', replyAuthor);
  // replyForm.append('commentId', commentId);
  // replyForm.append('content', replyContent);
  // replyForm.append('userId', userId);
  // replyForm.append('replyId', replyId);

  useEffect(() => {
    refetch();
  }, []);

  if (isLoading) return;
  return (
    <div>
      <div className={styles['comment-title']}>
        <h3>댓글창</h3>
      </div>
      <div className={styles['comment-container']}>
        <table>
          {data.commentList.map((item, index) => {
            return (
              <div key={index}>
                <div>
                  <div className={styles['comment-row']}>
                    <div className={styles['comment-author-content']}>
                      <tr key={index} className={styles['comment-row']}>
                        <th>{item.content}</th>
                        {/* <th className={styles['comment-author']}>{item.author}</th> */}
                      </tr>
                    </div>
                    <div className={styles['comment-crud']}>
                      <button
                        onClick={e => {
                          onDelete(item.commentId);
                        }}
                        className={styles['comment-delete']}
                      >
                        삭제
                      </button>
                      <button onClick={onClickUpdateHide} text="숨기기" className={styles['comment-update']}>
                        수정
                      </button>
                      <button onClick={onClickReplyHide} text="숨기기">
                        답글달기
                      </button>
                    </div>
                  </div>
                  {
                    // 수정 버튼 클릭시 나오는 대댓글 작성 폼
                    update === false && (
                      <div>
                        <div>
                          <form
                            onSubmit={e => {
                              e.preventDefault();
                            }}
                          >
                            <div>
                              <input
                                type="textfiled"
                                placeholder="내용을 입력하세요"
                                onChange={e => setCommentContent(e.target.value)}
                              />
                            </div>
                          </form>
                        </div>
                        <button
                          onClick={e => {
                            onUpdate(item.commentId, commentForm);
                            onClickUpdateHide();
                            e.preventDefault();
                          }}
                        >
                          수정완료
                        </button>
                      </div>
                    )
                  }
                  {
                    // 답글달기 버튼 클릭시 나오는 대댓글 작성 폼
                    reply === false && (
                      <div>
                        <div>
                          <form
                            onSubmit={e => {
                              e.preventDefault();
                            }}
                          >
                            <div>
                              <input
                                type="textfiled"
                                placeholder="내용을 입력하세요"
                                onChange={e => setReplyContent(e.target.value)}
                              />
                            </div>
                          </form>
                          <div>
                            <button
                              type="submit"
                              onClick={e => {
                                replyForm.commentId = item.commentId;
                                mutation2();
                                onClickReplyHide();
                                e.preventDefault();
                              }}
                            >
                              답글등록
                            </button>
                          </div>
                        </div>
                      </div>
                    )
                  }
                  {/* 대댓글 리스트 */}
                  <div className={styles.reply}>
                    {item.replyList.map((replyItem, replyIndex) => {
                      return (
                        <>
                          <div>
                            <tr>
                              <th>{replyItem.author}</th>
                              <th>{replyItem.content}</th>
                            </tr>
                          </div>
                          <div>
                            <button
                              onClick={e => {
                                onReplyDelete(replyItem.replyId);
                                e.preventDefault();
                              }}
                            >
                              삭제
                            </button>
                          </div>
                          <dir>
                            <button onClick={onClickUpUpdateHide} text="숨기기">
                              수정
                            </button>
                          </dir>
                          {
                            // 수정 버튼 클릭시 나오는 대댓글 수정 폼
                            upUpdate === false && (
                              <div>
                                <div>
                                  <form
                                    onSubmit={e => {
                                      e.preventDefault();
                                    }}
                                  >
                                    <div>
                                      <input
                                        type="textfiled"
                                        placeholder="내용을 입력하세요"
                                        onChange={e => setReplyContent(e.target.value)}
                                      />
                                    </div>
                                  </form>
                                </div>
                                <button
                                  onClick={e => {
                                    replyForm.replyId = replyItem.replyId;
                                    onReplyUpdate(replyForm.replyId, replyForm);
                                    onClickUpUpdateHide();
                                    e.preventDefault();
                                  }}
                                >
                                  수정완료
                                </button>
                              </div>
                            )
                          }
                        </>
                      );
                    })}
                  </div>
                </div>
              </div>
            );
          })}
        </table>
      </div>
    </div>
  );
}
