import { file } from '@babel/types';
import { ConstructionOutlined } from '@mui/icons-material';
import { QueryClient, useMutation, useQueryClient } from '@tanstack/react-query';
import { createReport } from 'apis/api/report';
import axios from 'axios';
import { missingDogList } from 'mocks/data/missing';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './ReportCreateContent.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import TextField from '@mui/material/TextField';

export default function ReportCreateContent() {
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [state, setState] = useState('0');
  // 위도,경도로 수정
  const [place, setPlace] = useState('0');
  const [gender, setGender] = useState('0');
  const [breedName, setBreedName] = useState('0');
  const [weight, setWeight] = useState('0');
  const [neutered, setNeutered] = useState('0');
  const [content, setContent] = useState('');

  const [categoryEar, setCategoryEar] = useState('0');
  const [categoryColor, setCategoryColor] = useState('0');
  const [categoryPattern, setCategoryPattern] = useState('0');
  const [categoryTail, setCategoryTail] = useState('0');
  const [categoryCloth, setCategoryCloth] = useState('0');
  const [categoryClothColor, setCategoryClothColor] = useState('0');
  const [showImages, setShowImages] = useState([]);

  //  ========== 사진 업로드 ==========

  // 이미지 상대경로 저장
  const handleAddImages = event => {
    const imageLists = event.target.files;
    let imageUrlLists = [...showImages];

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
    }

    if (imageUrlLists.length > 3) {
      imageUrlLists = imageUrlLists.slice(0, 3);
    }

    setShowImages(imageUrlLists);
  };

  // X버튼 클릭 시 이미지 삭제
  const handleDeleteImage = id => {
    setShowImages(showImages.filter((_, index) => index !== id));
  };

  //  ===========================================

  let formData = new FormData();
  formData.append('title', title);

  for (let i = 0; i < 4; i++) {
    formData.append('fileUrlList', showImages[i]);
  }

  // ================= Mutation =================
  const { mutate: mutation } = useMutation(['createReport'], () => {
    return createReport(state, formData);
  });

  console.log('DATA: ', formData);
  // ===========================================

  return (
    <div>
      {/* <form onSubmit={handleSubmit}> */}
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
        }}
      >
        {/* ================== Title ==================== */}
        <div className={styles.container}>
          <div className={styles.title}>
            <input type="text" placeholder="제목" onChange={e => setTitle(e.target.value)} />
          </div>
          <div className={styles['container-inner']}>
            {/* =============================== */}

            {/* =================== Picture ================== */}

            <div className={styles['container-picture']}>
              <div>
                <div>
                  <div>
                    <label htmlFor="input-file" onChange={handleAddImages}>
                      <input type="file" id="input-file" multiple />
                      <span>사진추가</span>
                    </label>
                    {showImages.map((image, id) => (
                      <div key={id}>
                        <img className={styles.imageSize} src={image} alt={`${image}-${id}`} />
                        <button onClick={() => handleDeleteImage(id)}>Delete</button>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>

            {/* =============================== */}
            <div className={styles['container-content']}>
              <div className={styles['container-content-info']}>
                <div className={styles['container-content-info-inner']}>
                  <div className={styles['container-content-info-inner-list']}>
                    <div>상태</div>
                    <div>이름</div>
                    <div>성별</div>
                    <div>실종/발견 장소</div>
                    <div>견종</div>
                    <div>몸무게</div>
                    <div>중성화</div>
                  </div>

                  <div className={styles['container-content-info-inner-content']}>
                    <div>
                      <select name="" id="" onChange={e => setState(e.target.value)}>
                        <option value="0">실종</option>
                        <option value="1">임시보호</option>
                        <option value="2">완료</option>
                      </select>
                    </div>
                    <div>
                      <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} />
                    </div>
                    <div>
                      <input
                        type="radio"
                        value="0"
                        checked={gender === '0'}
                        onChange={e => setGender(e.target.value)}
                      />
                      남
                      <input
                        type="radio"
                        value="1"
                        checked={gender === '1'}
                        onChange={e => setGender(e.target.value)}
                      />
                      여
                    </div>

                    <div>
                      <select name="" id="" onChange={e => setPlace(e.target.value)}>
                        <option value="0">장소</option>
                        <option value="1">장소</option>
                        <option value="2">장소</option>
                      </select>
                    </div>
                    <div>
                      <select name="" id="" onChange={e => setBreedName(e.target.value)}>
                        <option selected disabled>
                          견종
                        </option>
                        <option value="0">웰시</option>
                        <option value="1">감자</option>
                        <option value="2">푸들</option>
                      </select>
                    </div>
                    <div>
                      <input type="number" placeholder="몸무게" onChange={e => setWeight(e.target.value)} />
                    </div>
                    <div>
                      <input
                        type="radio"
                        value="0"
                        checked={neutered === '0'}
                        onChange={e => setNeutered(e.target.value)}
                      />
                      유
                      <input
                        type="radio"
                        value="1"
                        checked={neutered === '1'}
                        onChange={e => setNeutered(e.target.value)}
                      />
                      무
                      <input
                        type="radio"
                        value="2"
                        checked={neutered === '2'}
                        onChange={e => setNeutered(e.target.value)}
                      />
                      모름
                    </div>
                  </div>
                </div>
              </div>
              <div className={styles['container-content-character']}>
                <div>
                  <div>귀</div>
                  <div>털색</div>
                  <div>무늬색</div>
                </div>
                <div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryEar(e.target.value)}>
                      <option selected disabled>
                        귀
                      </option>
                      <option value="0">접힘</option>
                      <option value="1">펴짐</option>
                      <option value="2">모름</option>
                    </select>
                  </div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryColor(e.target.value)}>
                      <option selected disabled>
                        털색
                      </option>
                      <option value="0">흰색</option>
                      <option value="1">검은색</option>
                      <option value="2">모름</option>
                    </select>
                  </div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryPattern(e.target.value)}>
                      <option selected disabled>
                        무늬색
                      </option>
                      <option value="0">흰색</option>
                      <option value="1">검은색</option>
                      <option value="2">모름</option>
                    </select>
                  </div>
                </div>
                <div>
                  <div>꼬리</div>
                  <div>옷착용</div>
                  <div>옷색</div>
                </div>
                <div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryTail(e.target.value)}>
                      <option selected disabled>
                        꼬리
                      </option>
                      <option value="0">말림</option>
                      <option value="1">안말림</option>
                      <option value="2">모름</option>
                    </select>
                  </div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryCloth(e.target.value)}>
                      <option selected disabled>
                        옷착용
                      </option>
                      <option value="0">예</option>
                      <option value="1">아니오</option>
                    </select>
                  </div>
                  <div>
                    <select name="" id="" onChange={e => setCategoryClothColor(e.target.value)}>
                      <option selected disabled>
                        옷색
                      </option>
                      <option value="0">빨강</option>
                      <option value="1">파랑</option>
                      <option value="2">노랑</option>
                      <option value="3">모름</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div>
            <CKEditor
              editor={ClassicEditor}
              onReady={editor => {
                // You can store the "editor" and use when it is needed.
                console.log('Editor is ready to use!', editor);
              }}
              config={{
                placeholder: '내용을 입력하세요.',
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                console.log({ event, editor, data });
              }}
              onBlur={(event, editor) => {
                console.log('Blur.', editor);
              }}
              onFocus={(event, editor) => {
                console.log('Focus.', editor);
              }}
            />
            <textarea name="" id="" cols="150" rows="30"></textarea>
          </div>
          <button type="submit">등록</button>
          <Link to="/report">
            <button>취소</button>
          </Link>
        </div>
      </form>
    </div>
  );
}
