import React, { useState, useEffect, useRef } from 'react';
import { CreateBoard } from 'apis/api/board';
import { useMutation, useQuery } from '@tanstack/react-query';
import styles from '../Write.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useNavigate } from 'react-router-dom';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import Test from '../../../assets/images/volunteer.png';
import { getBoardList } from 'apis/api/board';
import { CgCloseO } from 'react-icons/cg';

export default function AdoptionReviewWrite() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [typeCode, setTypeCode] = useState(parseInt(0));
  const [preFile, setPreFile] = useState([]);
  const [fileList, setFileList] = useState([]);
  const [file2, setFile2] = useState(0);
  const [file3, setFile3] = useState(0);
  const [fileListAlertOpen, setFileListAlertOpen] = useState(0);
  const fileListInput = useRef();

  useEffect(() => {
    if (preFile.length !== 0) {
      setFileListAlertOpen(0);
    }
  }, [preFile]);

  //  ========== 사진 업로드 ==========
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

  for (let i = 0; i < fileList.length; i++) {
    formData2.append('uploadFiles', fileList[i]);
  }

  const { mutate: mutation } = useMutation(['test'], () => {
    console.log('왓다잉');
    return CreateBoard(formData2);
  });

  const navigate = useNavigate();
  const onClickBtn = () => {
    navigate('/community/adoptionreview');
  };
  const handleSubmit = () => {
    if (preFile.length === 0) {
      setFileListAlertOpen(1);
    }
    mutation();
    alert('등록되었습니다.');
    onClickBtn();
  };

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
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
            <button onClick={handleSubmit}>글등록</button>
          </div>
        </div>
      </form>
    </div>
  );
}
