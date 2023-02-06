import { useMutation, useQuery } from '@tanstack/react-query';
import { createReport, updateReport } from 'apis/api/report';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './ReportCreateContent.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Select from 'react-select';
import Button from '@mui/material/Button';
import Test from '../../../assets/images/volunteer.png';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList } from 'utils/changeSelectTemplate';

export default function ReportCreateContent() {
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [typeCode, setTypeCode] = useState(0);
  const [place, setPlace] = useState('');
  const [genderCode, setGenderCode] = useState('3');
  const [breedId, setBreedId] = useState(0);
  const [kg, setKg] = useState(0);
  const [neutered, setNeutered] = useState(0);

  const [content, setContent] = useState('');
  const [categoryEar, setCategoryEar] = useState(0);
  const [categoryColor, setCategoryColor] = useState(0);
  const [categoryPattern, setCategoryPattern] = useState(0);
  const [categoryTail, setCategoryTail] = useState(0);
  const [categoryCloth, setCategoryCloth] = useState(0);
  const [categoryClothColor, setCategoryClothColor] = useState(0);
  const [uploadFile, setUploadFile] = useState([]);

  // Picture

  const handleAddImages = event => {
    const imageLists = event.target.files;
    let imageUrlLists = [...uploadFile];

    for (let i = 0; i < imageLists.length; i++) {
      const currentImageUrl = URL.createObjectURL(imageLists[i]);
      imageUrlLists.push(currentImageUrl);
    }

    if (imageUrlLists.length > 3) {
      imageUrlLists = imageUrlLists.slice(0, 3);
    }
    setUploadFile(imageUrlLists);
  };

  const handleDeleteImage = id => {
    setUploadFile(uploadFile.filter((_, index) => index !== id));
  };

  // FormData

  let formData = new FormData();
  formData.append('title', title);
  formData.append('name', name);
  formData.append('genderCode', genderCode);
  formData.append('breedId', breedId);
  formData.append('kg', kg);
  formData.append('neutered', neutered);
  formData.append('content', content);
  formData.append('categoryEar', categoryEar);
  formData.append('categoryColor', categoryColor);
  formData.append('categoryPattern', categoryPattern);
  formData.append('categoryTail', categoryTail);
  formData.append('categoryCloth', categoryCloth);
  formData.append('categoryClothColor', categoryClothColor);
  formData.append('typeCode', typeCode.value);
  formData.append('uploadFile', uploadFile);

  // POST (등록)

  const { mutate: mutation } = useMutation(['createReport'], () => {
    return createReport(formData);
  });

  // GET (견종 리스트)
  const { isLoading, data } = useQuery({
    queryKey: ['getBreedsList'],
    queryFn: () => getBreedsList(),
  });
  const breedData = data;
  if (isLoading) return;

  // Select Data

  const stateOpt = [
    { value: 0, label: '상태' },
    { value: 1, label: '실종' },
    { value: 2, label: '임시보호' },
    { value: 3, label: '완료' },
  ];

  const placeOpt = [
    { value: 0, label: '장소' },
    { value: 1, label: '부산' },
    { value: 2, label: '서울' },
    { value: 3, label: '대구' },
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

  // console.log(changeBreedList(breedData));

  return (
    <div>
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
          console.log('POST');
        }}
      >
        <div className={styles.container}>
          <div className={styles.title}>
            <input type="text" placeholder="글 제목" onChange={e => setTitle(e.target.value)} />
          </div>
          <div className={styles['container-info']}>
            <div className={styles['container-info-picture']}>
              <div>
                <div className={styles['container-info-picture-inner']}>
                  {uploadFile.length === 0 ? (
                    <img className={styles.thumbnail} src={Test} alt="" />
                  ) : (
                    <div>
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(0)}>
                          Delete
                        </button>
                      </div>
                      <img className={styles.thumbnail} src={uploadFile[0]} alt={0} />
                    </div>
                  )}

                  <div className={styles['container-info-picture-inner-sub']}>
                    {uploadFile.length === 0 || uploadFile.length === 1 ? (
                      <img className={styles.subPicture} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(1)}>
                          Delete
                        </button>

                        <img className={styles.subPicture} src={uploadFile[1]} alt={1} />
                      </div>
                    )}
                    {uploadFile.length === 0 || uploadFile.length === 1 || uploadFile.length === 2 ? (
                      <img className={styles.subPicture} src={Test} alt="" />
                    ) : (
                      <div className={styles['deleteButton-box']}>
                        <button className={styles.deleteButton} onClick={() => handleDeleteImage(2)}>
                          Delete
                        </button>

                        <img className={styles.subPicture} src={uploadFile[2]} alt={2} />
                      </div>
                    )}
                  </div>
                </div>
                <div className={styles.pictureButtonCont}>
                  {uploadFile.length < 3 ? (
                    <label htmlFor="file" onChange={handleAddImages}>
                      <div className={styles.pictureButton}>
                        사진추가
                        <input type="file" id="file" accept="image/*" className={styles.file} multiple />
                      </div>
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
                  <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} />
                </div>
                <div>
                  <Select options={stateOpt} onChange={setTypeCode} defaultValue={stateOpt[0]} />
                </div>
              </div>

              <div>
                <div className={styles['container-gender']}>
                  <div className={styles['container-radio']}>
                    <span>성별</span>
                    <div>
                      <input
                        type="radio"
                        value="1"
                        checked={genderCode === '1'}
                        onChange={e => setGenderCode(e.target.value)}
                      />
                      수컷
                      <input
                        type="radio"
                        value="2"
                        checked={genderCode === '2'}
                        onChange={e => setGenderCode(e.target.value)}
                      />
                      암컷
                      <input
                        type="radio"
                        value="3"
                        checked={genderCode === '3'}
                        onChange={e => setGenderCode(e.target.value)}
                      />
                      모름
                    </div>
                  </div>
                </div>
                <div>
                  <Select
                    options={changeBreedList(breedData)}
                    onChange={setBreedId}
                    defaultValue={changeBreedList(breedData)[breedId]}
                  />
                </div>
              </div>

              <div>
                <div>
                  <input type="number" placeholder="몸무게" onChange={e => setKg(e.target.value)} />
                </div>
                <div>
                  <div>
                    <Select options={neuteredOpt} onChange={setNeutered} defaultValue={neuteredOpt[0]} />
                  </div>
                </div>
              </div>
              <div>
                <Select options={placeOpt} onChange={setPlace} defaultValue={placeOpt[0]} />
              </div>
              <div>장소 넣을 공간</div>
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
                // console.log('Editor is ready to use!', editor);
              }}
              config={{
                placeholder: '실종견/임보견 상세정보',
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                // console.log({ event, editor, data });
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
