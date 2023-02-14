import React, { useState } from 'react';
import { CreateBoard } from 'apis/api/board';
import { useMutation } from '@tanstack/react-query';
import styles from '../Write.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useNavigate } from 'react-router-dom';

export default function InfoeWrite() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [typeCode, setTypeCode] = useState(parseInt(0));
  const [images, setImages] = useState([]);

  const handleImageChange = e => {
    const files = Array.from(e.target.files);
    setImages(files);
  };

  const uploadImages = useMutation(['uploadImages'], formData2 => CreateBoard(formData2));

  const handleSubmit = e => {
    e.preventDefault();
    let formData2 = new FormData();
    formData2.append('title', title);
    formData2.append('content', content);
    formData2.append('typeCode', typeCode);
    images.forEach((image, index) => {
      formData2.append(`uploadFile`, image);
    });
    const imagesArray = formData2.getAll('uploadFile');
    console.log(imagesArray);
    uploadImages.mutate(formData2);
    // Here, you can use an API to post the image data to a server
  };

  const navigate = useNavigate();
  const onClickBtn = () => {
    navigate('/community/info');
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
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
          <div>
            <input type="file" multiple onChange={handleImageChange} />
          </div>

          <div>
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
          </div>
          <button type="submit">글등록</button>
        </div>
      </form>
    </div>
  );
}
