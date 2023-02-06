import { useMutation } from '@tanstack/react-query';
import { createReport, updateReport } from 'apis/api/report';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './ReportCreateContent.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Select from 'react-select';
import Button from '@mui/material/Button';
import Test from '../../../assets/images/volunteer.png';

export default function ReportCreateContent({ items, nowState }) {
  // console.log('now', nowState);
  const item = items;
  const [title, setTitle] = useState(item.title);
  const [name, setName] = useState(item.name);
  const [state, setState] = useState(nowState);
  const [place, setPlace] = useState(item.place);
  const [gender, setGender] = useState(item.gender);
  const [breedName, setBreedName] = useState(item.breedName);
  const [weight, setWeight] = useState(item.weight);
  const [neutered, setNeutered] = useState(item.neutered);
  const [content, setContent] = useState(item.content);
  const [categoryEar, setCategoryEar] = useState(item.categoryEar);
  const [categoryColor, setCategoryColor] = useState(item.categoryColor);
  const [categoryPattern, setCategoryPattern] = useState(item.categoryPattern);
  const [categoryTail, setCategoryTail] = useState(item.categoryTail);
  const [categoryCloth, setCategoryCloth] = useState(item.categoryCloth);
  const [categoryClothColor, setCategoryClothColor] = useState(item.categoryClothColor);
  const [showImages, setShowImages] = useState(item.fileUrlList);

  // Picture
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

  const handleDeleteImage = id => {
    setShowImages(showImages.filter((_, index) => index !== id));
  };

  // FormData

  let formData = new FormData();
  formData.append('title', title);
  formData.append('name', name);
  formData.append('gender', gender);
  formData.append('breedName', breedName);
  formData.append('weight', weight);
  formData.append('neutered', neutered);
  formData.append('content', content);
  formData.append('content', content);
  formData.append('categoryEar', categoryEar);
  formData.append('categoryColor', categoryColor);
  formData.append('categoryPattern', categoryPattern);
  formData.append('categoryTail', categoryTail);
  formData.append('categoryCloth', categoryCloth);
  formData.append('categoryClothColor', categoryClothColor);

  for (let i = 0; i < 4; i++) {
    formData.append('fileUrlList', showImages[i]);
  }

  // Mutation

  const { mutate: mutation } = useMutation(['createReport'], () => {
    // 함수에 조건문 넣기
    if (state === 0) {
      return createReport(state, formData);
    }
    if (state === 1) {
      return updateReport(state, formData);
    }
  });

  // console.log('DATA: ', formData);
  // console.log(content);

  // Select Data

  const stateOpt = [
    { value: 0, label: '실종' },
    { value: 1, label: '임시보호' },
    { value: 2, label: '완료' },
  ];

  const placeOpt = [
    { value: 0, label: '장소' },
    { value: 1, label: '부산' },
    { value: 2, label: '서울' },
    { value: 3, label: '대구' },
  ];

  const breedOpt = [
    { value: 0, label: '견종' },
    { value: 1, label: '웰시코기' },
    { value: 2, label: '푸들' },
    { value: 3, label: '말티즈' },
  ];

  const neuteredOpt = [
    { value: 0, label: '중성화' },
    { value: 1, label: '유' },
    { value: 2, label: '무' },
    { value: 3, label: '모름' },
  ];

  const categoryEarOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '직립귀' },
    { value: 2, label: '박쥐귀' },
    { value: 3, label: '반직립귀' },
    { value: 4, label: '버튼귀' },
    { value: 5, label: '장미귀' },
    { value: 6, label: '쳐진귀' },
    { value: 7, label: '접힌귀' },
    { value: 8, label: 'V자귀' },
  ];
  const categoryTailOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '말려있음' },
    { value: 2, label: '펴져있음' },
  ];

  const categoryColorOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '검정색' },
    { value: 2, label: '갈색' },
    { value: 3, label: '흰색' },
  ];
  const categoryPatternOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '얼룩무늬' },
    { value: 2, label: '민무늬' },
  ];
  const categoryClothOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '옷입음' },
    { value: 2, label: '안입음' },
  ];

  const categoryClothColorOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '빨강' },
    { value: 2, label: '파랑' },
  ];

  // Console

  console.log(
    //   title,
    //   name,
    //   state,
    //   place,
    //   gender,
    //   breedName,
    //   weight,
    //   neutered,
    //   content,
    //   categoryEar,
    //   categoryColor,
    //   categoryPattern,
    //   categoryTail,
    //   categoryCloth,
    //   categoryClothColor,
    showImages
  );

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
        }}
      >
        <div className={styles.container}>
          <div className={styles.title}>
            <input type="text" placeholder="글 제목" onChange={e => setTitle(e.target.value)} value={title} />
          </div>
          <div className={styles['container-info']}>
            <div className={styles['container-info-picture']}>
              <div>
                <div className={styles['container-info-picture-inner']}>
                  {showImages.length === 0 ? (
                    <img className={styles.thumbnail} src={Test} alt="" />
                  ) : (
                    <div>
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(0)}>
                          Delete
                        </button>
                      </div>
                      <img className={styles.thumbnail} src={showImages[0]} alt={0} />
                    </div>
                  )}

                  <div className={styles['container-info-picture-inner-sub']}>
                    {showImages.length === 0 || showImages.length === 1 ? (
                      <img className={styles.subPicture} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(1)}>
                          Delete
                        </button>

                        <img className={styles.subPicture} src={showImages[1]} alt={1} />
                      </div>
                    )}
                    {showImages.length === 0 || showImages.length === 1 || showImages.length === 2 ? (
                      <img className={styles.subPicture} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(2)}>
                          Delete
                        </button>

                        <img className={styles.subPicture} src={showImages[2]} alt={2} />
                      </div>
                    )}
                  </div>
                </div>
                <div className={styles.pictureButtonCont}>
                  {showImages.length < 3 ? (
                    <label htmlFor="file" onChange={handleAddImages} className={styles.pictureButton}>
                      사진추가
                      <input type="file" id="file" className={styles.file} multiple />
                    </label>
                  ) : (
                    <div> 3장까지 업로드 가능합니다. </div>
                  )}
                </div>
              </div>
            </div>

            <div className={styles['container-info-content']}>
              <div>
                <div>
                  <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} value={name} />
                </div>
                <div>
                  <Select options={stateOpt} onChange={setState} defaultValue={stateOpt[state]} />
                </div>
              </div>

              <div>
                <div className={styles['container-gender']}>
                  <div className={styles['container-radio']}>
                    <span>성별</span>
                    <div>
                      <input type="radio" value="0" checked={gender === 0} onChange={e => setGender(e.target.value)} />
                      수컷
                      <input type="radio" value="1" checked={gender === 1} onChange={e => setGender(e.target.value)} />
                      암컷
                    </div>
                  </div>
                </div>
                <div>
                  <Select options={placeOpt} onChange={setPlace} defaultValue={placeOpt[0]} />
                </div>
              </div>

              <div>
                <Select options={breedOpt} onChange={setBreedName} defaultValue={breedOpt[breedName]} />
              </div>
              <div>
                <div>
                  <input type="number" placeholder="몸무게" onChange={e => setWeight(e.target.value)} value={weight} />
                </div>
                <div>
                  <div>
                    <Select options={neuteredOpt} onChange={setNeutered} defaultValue={neuteredOpt[neutered]} />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className={styles.subTitle}>카테고리 등록 (선택사항)</div>
        <hr />
        <div className={styles.container}>
          <div className={styles['container-character']}>
            <div>
              <div>
                <span>귀</span>
                <div>
                  <Select
                    options={categoryEarOpt}
                    onChange={setCategoryEar}
                    defaultValue={categoryEarOpt[categoryEar]}
                  />
                </div>
              </div>

              <div>
                <span>털색</span>
                <div>
                  <Select
                    options={categoryColorOpt}
                    onChange={setCategoryColor}
                    defaultValue={categoryColorOpt[categoryColor]}
                  />
                </div>
              </div>
              <div>
                <span>무늬</span>
                <div>
                  <Select
                    options={categoryPatternOpt}
                    onChange={setCategoryPattern}
                    defaultValue={categoryPatternOpt[categoryPattern]}
                  />
                </div>
              </div>
            </div>

            <div>
              <div>
                <span>꼬리</span>
                <div>
                  <Select
                    options={categoryTailOpt}
                    onChange={setCategoryTail}
                    defaultValue={categoryTailOpt[categoryTail]}
                  />
                </div>
              </div>
              <div>
                <span>옷착용</span>
                <div>
                  <Select
                    options={categoryClothOpt}
                    onChange={setCategoryCloth}
                    defaultValue={categoryClothOpt[categoryCloth]}
                  />
                </div>
              </div>
              <div>
                <span>옷색</span>
                <div>
                  <Select
                    options={categoryClothColorOpt}
                    onChange={setCategoryCloth}
                    defaultValue={categoryClothColorOpt[categoryClothColor]}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className={styles.subTitle}>상세특징</div>
        <hr />
        <div>
          <div className={styles.ckEditor}>
            <CKEditor
              editor={ClassicEditor}
              onReady={editor => {
                // You can store the "editor" and use when it is needed.
                console.log('Editor is ready to use!', editor);
              }}
              config={{
                placeholder: '실종견/임보견 상세정보',
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                console.log({ event, editor, data });
                setContent(data);
              }}
            />
          </div>
          <div className={styles['container-button']}>
            <Button variant="contained" type="submit" className={styles.button}>
              등록
            </Button>
            <Link to="/report">
              <Button variant="contained" className={styles.button}>
                취소
              </Button>
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}
