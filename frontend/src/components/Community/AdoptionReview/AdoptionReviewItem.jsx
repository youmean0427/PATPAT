import Card from 'components/Common/Card';
import React from 'react';
import styles from './AdoptionReviewItem.module.scss';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 800,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function AdoptionReviewItem({ item }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <Button onClick={handleOpen}>
        <Card>
          <img src={item.thumbnail} alt="thumbnail" />
          <div className={styles['desc-wrap']}>
            <div className={styles.title}>{item.title}</div>
            <div className={styles.content}>{item.content}</div>
          </div>
        </Card>
      </Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <div className={styles['detail-view-wrapper']}>
            {item ? (
              <>
                <div className={styles['detail-title']}>
                  <label>{item.title}</label>
                </div>
                <div className={styles['sub-title']}>
                  <div className={styles['detail-view-row']}>
                    <div>{item.author}</div>
                  </div>
                  <div className={styles['detail-view-row']}>
                    <label>{item.count}</label>
                  </div>
                  <div className={styles['detail-view-row']}>
                    <label>{item.registDate}</label>
                  </div>
                  <div className={styles['detail-btn']}>
                    <button>삭제</button>
                  </div>
                  <div className={styles['detail-btn']}>
                    <button onClick={() => console.log('눌렷다 눌렷어')}>수정</button>
                  </div>
                </div>
                <div className={styles['detail-img']}>
                  <img src={item.thumbnail} alt="thumbnail" />
                </div>
                <div className={styles['detail-view-content']}>
                  <div>{item.content}</div>
                </div>
              </>
            ) : (
              '해당 게시글을 찾을 수 없습니다.'
            )}
            <div className={styles['detail-reply']}> 댓글창 </div>
            <div>
              <input type="text" placeholder="댓글을 입력해주세요" />
              <div>
                <Button className={style['detail-btn']}>이전</Button>
                <Button className={style['detail-btn']}>목록</Button>
                <Button className={style['detail-btn']}>다음</Button>
              </div>
            </div>
          </div>
        </Box>
      </Modal>
    </div>
  );
}
