import React, { useState } from 'react';
import { UpdateBoard } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from '../Write.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default function InfoUpdate() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state.item;
  const [fileUrlList, setFileUrlList] = useState([]);
  const [title, setTitle] = useState(data.title);
  const [content, setContent] = useState(data.content);
  const typeCode = data.typeCode;
  const boardId = data.boardId;

  const handleAddImages = event => {
    const imageLists = event.target.files;
    let imageUrlLists = [...fileUrlList];

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
    }

    if (imageUrlLists.length > 19) {
      imageUrlLists = imageUrlLists.slice(0, 10);
    }

    setFileUrlList(imageUrlLists);
  };

  // X버튼 클릭 시 이미지 삭제
  const handleDeleteImage = id => {
    setFileUrlList(fileUrlList.filter((_, index) => index !== id));
  };

  let formData2 = new FormData();
  formData2.append('title', title);
  formData2.append('content', content);
  formData2.append('typeCode', typeCode);
  formData2.append('boardId', boardId);

  for (let i = 0; i < 4; i++) {
    formData2.append('uploadFile', fileUrlList[i]);
  }

  const { mutate: mutation } = useMutation(['boardReviewList'], () => {
    return UpdateBoard(boardId, formData2);
  });

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

          // DeleteBoard(data.boardId);
        }}
      >
        <div className={styles.container}>
          <div>
            <input type="text" placeholder={title} onChange={e => setTitle(e.target.value)} />
          </div>
          <div>
            <div>
              <label htmlFor="input-file" onChange={handleAddImages}>
                <input type="file" id="input-file" multiple />
              </label>
              {fileUrlList.map((image, id) => (
                <div key={id}>
                  <img src={image} alt={`${image}-${id}`} />
                  <button onClick={() => handleDeleteImage(id)}>Delete</button>
                </div>
              ))}
            </div>
          </div>
          <div className={styles.ckEditor}>
            <CKEditor
              editor={ClassicEditor}
              config={{
                placeholder: content,
              }}
              onChange={(event, editor) => {
                const newdata = editor.getData();
                setContent(newdata);
              }}
            />
          </div>
          <button type="submit">수정완료</button>
        </div>
      </form>
    </div>
  );
}
