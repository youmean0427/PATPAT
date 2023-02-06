import { useMutation, useQuery } from '@tanstack/react-query';
import { createReport, updateReport } from 'apis/api/report';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './ReportUpdateContent.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Select from 'react-select';
import Button from '@mui/material/Button';
import Test from '../../../assets/images/volunteer.png';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList } from 'utils/changeSelectTemplate';

export default function ReportUpdateContent(items) {
  const item = items.items;
  const [title, setTitle] = useState(item.title);
  const [name, setName] = useState(item.name);
  const [typeCode, setTypeCode] = useState({ value: item.typeCode });
  const [place, setPlace] = useState('');
  const [genderCode, setGenderCode] = useState('1');
  const [breedId, setBreedId] = useState({ value: item.breedId });
  const [kg, setKg] = useState(item.kg);
  const [neutered, setNeutered] = useState({ value: item.neutered });

  const [content, setContent] = useState(item.content);
  const [categoryEar, setCategoryEar] = useState({ value: item.categoryEar });
  const [categoryColor, setCategoryColor] = useState({ value: item.categoryColor });
  const [categoryPattern, setCategoryPattern] = useState({ value: item.categoryPattern });
  const [categoryTail, setCategoryTail] = useState({ value: item.categoryTail });
  const [categoryCloth, setCategoryCloth] = useState({ value: item.categoryCloth });
  const [categoryClothColor, setCategoryClothColor] = useState({ value: item.categoryClothColor });
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
  formData.append('breedId', breedId.value);
  formData.append('kg', kg);
  formData.append('neutered', neutered.value);
  formData.append('content', content);
  formData.append('categoryEar', categoryEar.value);
  formData.append('categoryColor', categoryColor.value);
  formData.append('categoryPattern', categoryPattern.value);
  formData.append('categoryTail', categoryTail.value);
  formData.append('categoryCloth', categoryCloth.value);
  formData.append('categoryClothColor', categoryClothColor.value);
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
    { value: 1, label: '실종' },
    { value: 2, label: '임시보호' },
    { value: 3, label: '완료' },
  ];

  const placeOpt = [
    { value: 1, label: '부산' },
    { value: 2, label: '서울' },
    { value: 3, label: '대구' },
  ];

  const neuteredOpt = [
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
            <input type="text" placeholder="글 제목" onChange={e => setTitle(e.target.value)} value={title} />
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
                  <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} value={name} />
                </div>
                <div>
                  <Select
                    options={stateOpt}
                    onChange={setTypeCode}
                    placeholder="상태"
                    defaultValue={stateOpt[typeCode.value]}
                  />
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
                    placeholder="견종"
                    defaultValue={changeBreedList(breedData)[breedId.value]}
                  />
                </div>
              </div>

              <div>
                <div>
                  <input type="number" placeholder="몸무게" onChange={e => setKg(e.target.value)} value={kg} />
                </div>
                <div>
                  <div>
                    <Select
                      options={neuteredOpt}
                      onChange={setNeutered}
                      placeholder="중성화"
                      defaultValue={neuteredOpt[neutered.value]}
                    />
                  </div>
                </div>
              </div>
              <div>
                <Select options={placeOpt} onChange={setPlace} placeholder="장소" />
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
                    defaultValue={categoryEarOpt[categoryEar.value]}
                  />
                </div>
              </div>

              <div>
                <span>털색</span>
                <div>
                  <Select
                    options={categoryColorOpt}
                    onChange={setCategoryColor}
                    defaultValue={categoryColorOpt[categoryColor.value]}
                  />
                </div>
              </div>
              <div>
                <span>무늬</span>
                <div>
                  <Select
                    options={categoryPatternOpt}
                    onChange={setCategoryPattern}
                    defaultValue={categoryPatternOpt[categoryPattern.value]}
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
                    defaultValue={categoryTailOpt[categoryTail.value]}
                  />
                </div>
              </div>
              <div>
                <span>옷착용</span>
                <div>
                  <Select
                    options={categoryClothOpt}
                    onChange={setCategoryCloth}
                    defaultValue={categoryClothOpt[categoryCloth.value]}
                  />
                </div>
              </div>
              <div>
                <span>옷색</span>
                <div>
                  <Select
                    options={categoryClothColorOpt}
                    onChange={setCategoryCloth}
                    defaultValue={categoryClothColorOpt[categoryClothColor.value]}
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
              data={item.content}
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
