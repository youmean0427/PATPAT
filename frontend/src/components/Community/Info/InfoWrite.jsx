import React, { useState, useRef } from 'react';
import { CreateBoard } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';
import styles from '../Write.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useNavigate } from 'react-router-dom';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import Test from '../../../assets/images/volunteer.png';

export default function InfoeWrite() {
  const [fileUrlList, setFileUrlList] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  // const registDate = '2021-02-03';
  const [typeCode, setTypeCode] = useState(parseInt(0));
  const [preFile, setPreFile] = useState([]);
  const [fileList, setFileList] = useState([]);
  // const [boardId, setBoardId] = useState(0);
  const userId = 2;
  const [fileListAlertOpen, setFileListAlertOpen] = useState(0);
  const fileListInput = useRef();
  //  ========== 사진 업로드 ==========
  // 이미지 상대경로 저장
  const handleAddImages = e => {
    const imageFileList = [...fileList];
    let imageUrlLists = [...preFile];
    const imageLists = e.target.files;

    for (let i = 0; i < imageLists.length; i++) {
      imageFileList.push(imageLists[i]);
    }

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
    }
    setPreFile(imageUrlLists);
    setFileList(imageFileList);
  };

  // X버튼 클릭 시 이미지 삭제
  const handleDeleteImage = id => {
    setPreFile(preFile.filter((_, index) => index !== id));
    setFileList(fileList.filter((_, index) => index !== id));
  };

  let formData2 = new FormData();
  formData2.append('title', title);
  formData2.append('content', content);
  formData2.append('typeCode', typeCode);
  // formData2.append('userId', userId);
  // formData2.append('author', author);
  // formData2.append('registDate', registDate);
  // formData2.append('boardId', boardId);

  const { mutate: mutation } = useMutation(['test'], () => {
    return CreateBoard(formData2);
  });

  const navigate = useNavigate();
  const onClickBtn = () => {
    navigate('/community/info');
  };

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
          onClickBtn();
        }}
      >
        <div className={styles.container}>
          <div>
            <select onChange={e => setTypeCode(parseInt(e.target.value))}>
              <option value={0}>입양후기</option>
              <option value={1}>무료나눔</option>
              <option value={2}>정보공유</option>
            </select>
          </div>
          <div>
            <input type="text" placeholder="제목을 입력하세요" onChange={e => setTitle(e.target.value)} />
          </div>
          <div className={styles['container-FreeShare-picture-inner']}>
            {preFile.length === 0 ? (
              <img className={styles.thumbnail} src={Test} alt="" />
            ) : (
              <div>
                <div className={styles['deleteButton-box']}>
                  <button className={styles.deleteButton} onClick={() => handleDeleteImage(0)}>
                    Delete
                  </button>
                </div>
                <img className={styles.thumbnail} src={preFile[0]} alt="" />
              </div>
            )}

            <div className={styles['container-FreeShare-picture-inner-sub']}>
              {preFile.length === 0 || preFile.length === 1 ? (
                <img className={styles.subPicture} src={Test} alt="" />
              ) : (
                <div className={styles['deleteButton-box']}>
                  <button className={styles.deleteButton} onClick={() => handleDeleteImage(1)}>
                    Delete
                  </button>

                  <img className={styles.subPicture} src={preFile[1]} alt={1} />
                </div>
              )}
              {preFile.length === 0 || preFile.length === 1 || preFile.length === 2 ? (
                <img className={styles.subPicture} src={Test} alt="" />
              ) : (
                <div className={styles['deleteButton-box']}>
                  <button className={styles.deleteButton} onClick={() => handleDeleteImage(2)}>
                    Delete
                  </button>

                  <img className={styles.subPicture} src={preFile[2]} alt={2} />
                </div>
              )}
            </div>
          </div>
          <div className={styles.pictureButtonCont}>
            {preFile.length < 3 ? (
              <label htmlFor="file" onChange={handleAddImages}>
                <div className={styles.pictureButton}>
                  사진추가
                  <input ref={fileListInput} type="file" id="file" accept="image/*" className={styles.file} multiple />
                </div>
              </label>
            ) : (
              <div> 3장까지 업로드 가능합니다. </div>
            )}{' '}
            {fileListAlertOpen === 0 ? null : (
              <div>
                <Stack sx={{ width: '100%' }} spacing={2}>
                  <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                    사진을 1장 이상 추가해주세요.
                  </Alert>
                </Stack>
              </div>
            )}
          </div>

          <div className={styles.ckEditor}>
            <CKEditor
              editor={ClassicEditor}
              config={{
                placeholder: '내용을 입력하세요',
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                setContent(data);
              }}
            />
          </div>
          <button type="submit">글등록</button>
        </div>
      </form>
    </div>
  );
}
