import React, { useState, useRef, useEffect } from 'react';
import styles from './CommunityUpdate.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Test from '../../../assets/images/volunteer.png';
import './ckeditor.scss';
import { useMutation } from '@tanstack/react-query';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

import { CgCloseO } from 'react-icons/cg';
import { Link } from 'react-router-dom';
import { UpdateBoard } from 'apis/api/board';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router-dom';

export default function CommunityUpdate() {
  const data = useLocation().state.data;
  const stateCode = useLocation().state.stateCode;
  const navigate = useNavigate();
  const [title, setTitle] = useState(data.title);
  const [content, setContent] = useState(data.content);
  const images = [];
  data.fileUrlList.forEach(element => {
    images.push(element.filePath);
  });
  // Picture
  const [preFile, setPreFile] = useState(images);
  const prevFile = images;
  const [fileList, setFileList] = useState(images);
  const [file2, setFile2] = useState(0);
  const [file3, setFile3] = useState(0);

  const [titleAlertOpen, setTitleAlertOpen] = useState(0);
  const [fileListAlertOpen, setFileListAlertOpen] = useState(0);
  const [contentAlertOpen, setContentAlertOpen] = useState(0);

  const titleInput = useRef();
  const fileListInput = useRef();
  const contentInput = useRef();

  // useEffect
  useEffect(() => {
    if (title !== '') {
      setTitleAlertOpen(0);
    }
  }, [title]);

  useEffect(() => {
    if (preFile.length !== 0) {
      setFileListAlertOpen(0);
    }
  }, [preFile]);

  useEffect(() => {
    if (content !== '') {
      setContentAlertOpen(0);
    }
  }, [content]);

  const convertURLtoFile = async url => {
    const response = await fetch(url);
    const data = await response.blob();
    const ext = url.split('.').pop(); // url 구조에 맞게 수정할 것
    const filename = url.split('/').pop(); // url 구조에 맞게 수정할 것
    const metadata = { type: `image/${ext}` };
    return new File([data], filename, metadata);
  };

  // Picture - 이전이미지 수정 안됨
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

  // Submit
  const handleSubmit = () => {
    if (content === '') {
      setContentAlertOpen(1);
      contentInput.current.focus();
    }

    if (preFile.length === 0) {
      setFileListAlertOpen(1);
    }

    if (title === '') {
      setTitleAlertOpen(1);
      titleInput.current.focus();
    }

    if (content !== '' && title !== '') {
      mutation();
      Swal.fire({
        icon: 'info',
        title: '수정하시겠습니까?',
        showCancelButton: true,
        confirmButtonText: '수정',
        cancelButtonText: '취소',
      }).then(() =>
        stateCode === 0
          ? navigate('/community/adoption')
          : stateCode === 1
          ? navigate('/community/share')
          : navigate('/community/info')
      );
    }
  };

  let formData = new FormData();
  formData.append('title', title);
  formData.append('content', content);
  formData.append('staeCode', 0);
  formData.append('typeCode', stateCode);

  for (let i = 0; i < fileList.length; i++) {
    formData.append('uploadFile', fileList[i]);
  }

  const { mutate: mutation } = useMutation(['UpdateBoard'], () => {
    return UpdateBoard(data.boardId, formData);
  });

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
        }}
      >
        <div className={styles.container}>
          <div className={styles.title}>
            <input ref={titleInput} type="text" defaultValue={title} onChange={e => setTitle(e.target.value)} />
            <div>
              {titleAlertOpen === 0 ? null : (
                <div>
                  <Stack sx={{ width: '100%' }} spacing={2}>
                    <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                      제목을 작성해주세요.
                    </Alert>
                  </Stack>
                </div>
              )}
            </div>
          </div>
          <div className={styles['container-info']}>
            <div className={styles['container-info-picture']}>
              <div>
                <div className={styles['container-info-picture-inner']}>
                  {preFile.length === 0 ? (
                    <img className={styles.thumbnail} src={Test} alt="" />
                  ) : (
                    <div>
                      {file2 === 1 ? (
                        <img className={styles.thumbnail} src={preFile[1]} alt="" />
                      ) : file3 === 1 ? (
                        <img className={styles.thumbnail} src={preFile[2]} alt="" />
                      ) : (
                        <div>
                          <div className={styles['deleteButton-box']}>
                            <button className={styles.deleteButton} onClick={() => handleDeleteImage(0)}>
                              <CgCloseO className={styles.deleteButtonColor} />
                            </button>
                          </div>
                          <img className={styles.thumbnail} src={preFile[0]} alt="" />
                        </div>
                      )}
                    </div>
                  )}

                  <div className={styles['container-info-picture-inner-sub']}>
                    {preFile.length === 0 || preFile.length === 1 ? (
                      <img className={styles.subPictureNon} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(1)}>
                          <CgCloseO />
                        </button>

                        <img
                          className={styles.subPicture}
                          src={preFile[1]}
                          alt={1}
                          onMouseEnter={() => {
                            setFile2(1);
                          }}
                          onMouseLeave={() => {
                            setFile2(0);
                          }}
                          onClick={() => {
                            let item = preFile.splice(1, 1);
                            preFile.splice(0, 0, item[0]);
                            let item1 = fileList.splice(1, 1);
                            fileList.splice(0, 0, item1[0]);
                          }}
                        />
                      </div>
                    )}
                    {preFile.length === 0 || preFile.length === 1 || preFile.length === 2 ? (
                      <img className={styles.subPictureNon} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(2)}>
                          <CgCloseO />
                        </button>
                        <div className={styles['subPicture-cont']}>
                          <img
                            className={styles.subPicture}
                            src={preFile[2]}
                            alt={2}
                            onMouseEnter={() => {
                              setFile3(1);
                            }}
                            onMouseLeave={() => {
                              setFile3(0);
                            }}
                            onClick={() => {
                              let item = preFile.splice(2, 1);
                              preFile.splice(0, 0, item[0]);
                              let item1 = fileList.splice(2, 1);
                              fileList.splice(0, 0, item1[0]);
                            }}
                          />
                        </div>
                      </div>
                    )}
                  </div>
                </div>
                <div className={styles.pictureButtonCont}>
                  {preFile.length < 3 ? (
                    <label htmlFor="file" onChange={handleAddImages}>
                      <div className={styles.pictureButton}>
                        사진추가
                        <input
                          ref={fileListInput}
                          type="file"
                          id="file"
                          accept="image/*"
                          className={styles.file}
                          multiple
                        />
                      </div>
                    </label>
                  ) : (
                    <div> 3장까지 업로드 가능합니다. </div>
                  )}
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
              </div>
            </div>
          </div>
        </div>
        <hr />
        <div>
          {contentAlertOpen === 0 ? null : (
            <div>
              <Stack sx={{ width: '100%' }} spacing={2}>
                <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                  정성스런 게시물 작성은 PATPAT에게 큰 힘이 됩니다.
                </Alert>
              </Stack>
            </div>
          )}
          <div className={styles.ckEditor}>
            <CKEditor
              data={content}
              editor={ClassicEditor}
              config={{
                placeholder: '상세',
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                setContent(data);
              }}
            />
          </div>
        </div>
        <hr />
        <div>
          <div className={styles['container-button']}>
            <button onClick={handleSubmit} className={styles.button}>
              <div>등록</div>
            </button>
            <Link to="/report" className="links">
              <button className={styles.button}>
                <div>취소</div>
              </button>
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}
