import React, { useState, useEffect } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getBoardDetail, CreateComment } from 'apis/api/board';
import styles from './Comment.module.scss';
import { DeleteComment, UpdateComment, CreateReply, DeleteReply, UpdateReply } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';

//27 > boardId
export default function Comment() {
  const { data, isLoading, refetch } = useQuery({
    queryKey: ['commentList'],
    queryFn: () => getBoardDetail(27),
  });
  const [content, setContent] = useState('');
  useEffect(() => {
    if (data) {
      refetch();
    }
  }, []);
  let commentData = { boardId: 27, content: content };

  const { mutate: mutation } = useMutation(['commentList'], () => {
    getBoardDetail(27);
    refetch();
    return CreateComment(commentData);
  });

  // 댓글 삭제시 리렌더링``
  const onDelete = commentId => {
    DeleteComment(commentId).then(res => {
      refetch();
    });
  };

  // 수정 시 리렌더링
  const onUpdate = (commentId, commentData) => {
    UpdateComment(commentId, commentData).then(res => {
      setUpdate(true);
      refetch();
    });
  };

  // 대댓글 생성
  const { mutate: mutation2 } = useMutation(['replyList'], () => {
    setShowReply(true);
    getBoardDetail(27);
    refetch();
    return CreateReply(replyForm);
  });

  // 대댓글 삭제 시 리렌더링
  const onReplyDelete = replyId => {
    DeleteReply(replyId).then(res => {
      refetch();
    });
  };

  // 대댓글 수정
  const onReplyUpdate = (replyId, replyForm) => {
    UpdateReply(replyId, replyForm).then(res => {
      setUpUpdate(true);
      refetch();
    });
  };

  // 수정 클릭 시 reply content 넣는 input div 보이기

  const [showReply, setShowReply] = useState({});
  function onClickReplyHide(index) {
    setShowReply({
      ...showReply,
      [index]: !showReply[index],
    });
    refetch();
  }

  const [update, setUpdate] = useState(true);
  function onClickUpdateHide(index) {
    setUpdate({ ...update, [index]: !update[index] });
  }
  const [upUpdate, setUpUpdate] = useState(true);
  function onClickUpUpdateHide(index) {
    setUpUpdate({ ...upUpdate, [index]: !upUpdate[index] });
  }
  const [commentContent, setCommentContent] = useState('');

  const [replyContent, setReplyContent] = useState('');
  let replyId = 0;
  let commentId = 0;
  let commentForm = { content: commentContent, commentId: commentId };
  // 대댓글 정보
  let replyForm = { content: replyContent, replyId: replyId };

  useEffect(() => {
    refetch();
  }, []);
  if (isLoading) return;

  return (
    <div>
      <div className={styles['comment-title']}>
        <h2>댓글</h2>
      </div>
      <div className={styles['comment-container']}>
        <table>
          {data.commentList.map((item, index) => {
            return (
              <div key={item.commentId}>
                <div>
                  <div className={styles['comment-row']}>
                    <div className={styles['comment-author-content']}>
                      <tr>
                        <th className={styles['comment-row-item']}>{item.author}</th>
                        <th className={styles['comment-row-item']}>{item.content}</th>
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
                      <button
                        onClick={() => onClickUpdateHide(index)}
                        text="숨기기"
                        className={styles['comment-update']}
                      >
                        수정
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();

                          onClickReplyHide(index);
                        }}
                      >
                        답글달기
                      </button>
                    </div>
                  </div>
                  {
                    // 답글달기 버튼 클릭시 나오는 대댓글 작성 폼
                    showReply[index] === false && (
                      <div>
                        <div>
                          <form
                            onSubmit={e => {
                              e.preventDefault();
                            }}
                          >
                            <div className={styles['input-row']}>
                              <input
                                className={styles['input-field']}
                                type="textfiled"
                                placeholder="내용을 입력하세요"
                                onChange={e => setReplyContent(e.target.value)}
                              />
                              <button
                                className={styles['input-btn']}
                                type="submit"
                                onClick={e => {
                                  e.preventDefault();
                                  replyForm.commentId = item.commentId;
                                  mutation2();
                                  onClickReplyHide();
                                }}
                              >
                                답글등록
                              </button>
                            </div>
                          </form>
                        </div>
                      </div>
                    )
                  }
                  {
                    // 수정 버튼 클릭시 나오는 댓글 수정 폼
                    update[index] === false && (
                      <div>
                        <div>
                          <form
                            onSubmit={e => {
                              e.preventDefault();
                            }}
                          >
                            <div className={styles['input-row']}>
                              <input
                                className={styles['input-field']}
                                type="textfiled"
                                placeholder="내용을 입력하세요"
                                onChange={e => setCommentContent(e.target.value)}
                              />
                              <button
                                className={styles['input-btn']}
                                onClick={e => {
                                  onUpdate(item.commentId, commentForm);
                                  onClickUpdateHide();
                                  e.preventDefault();
                                }}
                              >
                                수정완료
                              </button>
                            </div>
                          </form>
                        </div>
                      </div>
                    )
                  }
                  {/* 대댓글 리스트 */}
                  <div className={styles.reply}>
                    {item.replyList.map((replyItem, replyIndex) => {
                      return (
                        <>
                          <div className={styles['comment-row']}>
                            <div key={replyItem.replyId} className={styles['comment-author-content']}>
                              <tr>
                                <SubdirectoryArrowRightIcon />
                                <th className={styles['comment-row-item']}>{replyItem.author}</th>
                                <th className={styles['comment-row-item']}>{replyItem.content}</th>
                              </tr>
                            </div>
                            <div className={styles['comment-crud']}>
                              <div>
                                <button
                                  className={styles['comment-delete']}
                                  onClick={e => {
                                    onReplyDelete(replyItem.replyId);
                                    e.preventDefault();
                                  }}
                                >
                                  삭제
                                </button>
                              </div>
                              <div>
                                <button
                                  className={styles['comment-update']}
                                  onClick={() => onClickUpUpdateHide(replyIndex)}
                                  text="숨기기"
                                >
                                  수정
                                </button>
                              </div>
                            </div>
                          </div>
                          {
                            // 수정 버튼 클릭시 나오는 대댓글 수정 폼
                            upUpdate[replyIndex] === false && (
                              <div>
                                <div>
                                  <form
                                    onSubmit={e => {
                                      e.preventDefault();
                                    }}
                                  >
                                    <div className={styles['input-row']}>
                                      <input
                                        className={styles['input-field']}
                                        type="textfiled"
                                        placeholder="내용을 입력하세요"
                                        onChange={e => setReplyContent(e.target.value)}
                                      />
                                      <button
                                        className={styles['input-btn']}
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
                                  </form>
                                </div>
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
                className={styles['input-field']}
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
    </div>
  );
}
