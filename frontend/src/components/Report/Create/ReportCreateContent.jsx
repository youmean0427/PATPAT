import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { createReport } from 'apis/api/report';
import React, { useEffect, useState } from 'react';
import styles from './ReportCreateContent.module.scss';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import Select from 'react-select';
import Test from '../../../assets/images/volunteer.png';
import { getBreedsList } from 'apis/api/shelter';
import { changeBreedList, changeReportBreedList } from 'utils/changeSelectTemplate';
import './ckeditor.scss';

import { MapMarker, Map, Circle } from 'react-kakao-maps-sdk';

import infoIcon from 'assets/images/forpaw-info.png';
import DetailModal from 'components/Common/DetailModal';
import EarDetail from './EarDetail';
import PatternDetail from './PatternDetail';
import TailDetail from './TailDetail';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import { useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import { CgCloseO } from 'react-icons/cg';
import { toast } from 'react-toastify';

export default function ReportCreateContent() {
  const navigate = useNavigate();
  // info

  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [age, setAge] = useState(0);
  const [typeCode, setTypeCode] = useState({ value: 0 });
  const [position, setPosition] = useState({ lat: 37, lng: 127 });
  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [genderCode, setGenderCode] = useState(3);
  const [breedId, setBreedId] = useState({ value: 0 });
  const [kg, setKg] = useState(0);
  const [neuteredCode, setNeuteredCode] = useState({ value: 0 });
  const [content, setContent] = useState('');

  // category
  const [categoryEar, setCategoryEar] = useState({ value: 0 });
  const [categoryColor, setCategoryColor] = useState([]);
  const [categoryPattern, setCategoryPattern] = useState({ value: 0 });
  const [categoryTail, setCategoryTail] = useState({ value: 0 });
  const [categoryCloth, setCategoryCloth] = useState({ value: 0 });

  const [color1, setColor1] = useState('#000000');
  const [color2, setColor2] = useState('#000000');
  const [color3, setColor3] = useState('#000000');

  // Picture
  const [preFile, setPreFile] = useState([]);
  const [fileList, setFileList] = useState([]);
  const [file2, setFile2] = useState(0);
  const [file3, setFile3] = useState(0);

  // Modal
  const [modal, setModal] = useState(false);
  const [modalNum, setModalNum] = useState();

  let now = new Date();
  let todayYear = now.getFullYear();
  let todayMonth = now.getMonth() + 1;
  let todayDate = now.getDate();

  const registDate = todayYear + '.' + todayMonth + '.' + todayDate;

  // Alert
  const [titleAlertOpen, setTitleAlertOpen] = useState(0);
  const [fileListAlertOpen, setFileListAlertOpen] = useState(0);
  const [nameAlertOpen, setNameAlertOpen] = useState(0);
  const [typeCodeAlertOpen, setTypeCodeAlertOpen] = useState(0);
  const [breedAlertOpen, setBreedAlertOpen] = useState(0);
  const [positionAlertOpen, setPositionAlertOpen] = useState(0);
  const [contentAlertOpen, setContentAlertOpen] = useState(0);
  const [neuteredAlertOpen, setNeuteredAlertOpen] = useState(0);

  const titleInput = useRef();
  const fileListInput = useRef();
  const nameInput = useRef();
  const typeCodeInput = useRef();
  const breedInput = useRef();
  const positionInput = useRef();
  const contentInput = useRef();
  const neuteredInput = useRef();

  // useEffect

  useEffect(() => {
    setLat(position.lat);
    setLng(position.lng);
  }, [position]);

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
    if (name !== '') {
      setNameAlertOpen(0);
    }
  }, [name]);

  useEffect(() => {
    if (typeCode.value !== 0) {
      setTypeCodeAlertOpen(0);
    }
  }, [typeCode]);

  useEffect(() => {
    if (breedId.value !== 0) {
      setBreedAlertOpen(0);
    }
  }, [breedId]);
  useEffect(() => {
    if (lat !== 0 && lng !== 0) {
      setPositionAlertOpen(0);
    }
  }, [lat, lng]);

  useEffect(() => {
    if (content !== '') {
      setContentAlertOpen(0);
    }
  }, [content]);

  useEffect(() => {
    if (neuteredCode.value !== 0) {
      setNeuteredAlertOpen(0);
    }
  }, [neuteredCode]);

  useEffect(() => {
    setCategoryColor([color1, color2, color3]);
  }, [color1, color2, color3]);

  // Picture
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

  // formData.append('uploadFile', preFile);
  const queryClient = useQueryClient();
  const { mutate: mutation } = useMutation(['createReport'], formData => createReport(formData), {
    onSuccess: () => {
      queryClient.invalidateQueries(['missingDogList']);
      toast('등록되었습니다.', { type: 'success' });
      navigate('/report');
    },
  });

  // GET (견종 리스트)
  const { isLoading, data: breedData } = useQuery({
    queryKey: ['getBreedsList'],
    queryFn: () => getBreedsList(),
  });
  if (isLoading) return;

  // Select Data

  const stateOpt = [
    { value: 1, label: '실종' },
    { value: 2, label: '임시보호' },
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
    { value: 1, label: '말린꼬리' },
    { value: 2, label: '수달꼬리' },
    { value: 3, label: '당근꼬리' },
    { value: 4, label: '단발꼬리' },
  ];

  const categoryPatternOpt = [
    { value: 0, label: '모름' },
    { value: 1, label: '솔리드' },
    { value: 2, label: '바이컬러' },
    { value: 3, label: '트라이컬러' },
    { value: 4, label: '탄' },
    { value: 5, label: '턱시도' },
    { value: 6, label: '할리퀸/스팟' },
    { value: 7, label: '브린들' },
    { value: 8, label: '새들' },
    { value: 9, label: '세이블' },
    { value: 10, label: '멀' },
  ];
  const categoryClothOpt = [
    { value: 0, label: 'X' },
    { value: 1, label: 'O' },
  ];

  // Modal
  const openModal = idx => {
    setModalNum(idx);
    setModal(true);
  };

  const closeModal = () => {
    setModal(false);
  };
  // Console

  // Submit
  const handleSubmit = () => {
    if (content === '') {
      setContentAlertOpen(1);
      contentInput.current.focus();
    }
    if (lat === 0 && lng === 0) {
      setPositionAlertOpen(1);
      positionInput.current.focus();
    }
    if (breedId.value === 0) {
      setBreedAlertOpen(1);
      breedInput.current.focus();
    }
    if (preFile.length === 0) {
      setFileListAlertOpen(1);
    }
    if (typeCode.value === 0) {
      setTypeCodeAlertOpen(1);
      typeCodeInput.current.focus();
    }
    if (name === '') {
      setNameAlertOpen(1);
      nameInput.current.focus();
    }
    if (title === '') {
      setTitleAlertOpen(1);
      titleInput.current.focus();
    }
    if (neuteredCode.value === 0) {
      setNeuteredAlertOpen(1);
      neuteredInput.current.focus();
    }

    if (
      lat !== 0 &&
      lng !== 0 &&
      content !== '' &&
      breedId.value !== 0 &&
      typeCode.value !== 0 &&
      (name !== '') & (title !== '') &&
      neuteredCode.value !== 0
    ) {
      // FormData
      const formData = new FormData();
      formData.append('title', title);
      formData.append('name', name);
      formData.append('age', age);
      formData.append('genderCode', genderCode);
      formData.append('breedId', breedId.value);
      formData.append('kg', kg);
      formData.append('neuteredCode', neuteredCode.value);
      formData.append('content', content);
      formData.append('categoryEarCode', categoryEar.value);
      formData.append('categoryColor', categoryColor);
      formData.append('categoryPatternCode', categoryPattern.value);
      formData.append('categoryTailCode', categoryTail.value);
      formData.append('categoryClothCode', categoryCloth.value);
      formData.append('typeCode', typeCode.value);
      formData.append('latitude', lat);
      formData.append('longitude', lng);
      formData.append('stateCode', 0);
      // formData.append('date', registDate);
      // POST (등록)
      for (let i = 0; i < fileList.length; i++) {
        formData.append('uploadFile', fileList[i]);
      }
      // navigate('/report');
      mutation(formData);
    }
  };
  return (
    <div>
      <div className={styles.container}>
        <div className={styles.title}>
          <input ref={titleInput} type="text" placeholder="글 제목" onChange={e => setTitle(e.target.value)} />
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

          <div className={styles['container-info-content']}>
            <div>
              <div>
                <input ref={nameInput} type="text" placeholder="이름" onChange={e => setName(e.target.value)} />
                {nameAlertOpen === 0 ? null : (
                  <div>
                    <Stack sx={{ width: '100%' }} spacing={2}>
                      <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                        이름을 작성해주세요.
                      </Alert>
                    </Stack>
                  </div>
                )}
              </div>
              <div>
                <Select options={stateOpt} ref={typeCodeInput} onChange={setTypeCode} placeholder="상태" />
                {typeCodeAlertOpen === 0 ? null : (
                  <div>
                    <Stack sx={{ width: '100%' }} spacing={2}>
                      <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                        상태를 선택해주세요.
                      </Alert>
                    </Stack>
                  </div>
                )}
              </div>
            </div>

            <div>
              <div>
                <Select
                  options={changeBreedList(breedData)}
                  ref={breedInput}
                  onChange={setBreedId}
                  placeholder="전체 보기"
                />
                {breedAlertOpen === 0 ? null : (
                  <div>
                    <Stack sx={{ width: '100%' }} spacing={2}>
                      <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                        견종을 선택해주세요.
                      </Alert>
                    </Stack>
                  </div>
                )}
              </div>
            </div>

            <div>
              <div>
                <div className={styles['container-gender']}>
                  <div className={styles['container-radio']}>
                    <span>성별</span>
                    <div>
                      <div>
                        <input
                          type="radio"
                          value={parseInt(1)}
                          checked={genderCode === 1}
                          onChange={e => setGenderCode(parseInt(e.target.value))}
                        />
                        <span>수컷</span>
                      </div>
                      <div>
                        <input
                          type="radio"
                          value={parseInt(2)}
                          checked={genderCode === 2}
                          onChange={e => setGenderCode(parseInt(e.target.value))}
                        />
                        <span>암컷</span>
                      </div>
                      <div>
                        <input
                          type="radio"
                          value={parseInt(3)}
                          checked={genderCode === 3}
                          onChange={e => setGenderCode(parseInt(e.target.value))}
                        />
                        <span>모름</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <input type="number" placeholder="나이" onChange={e => setAge(e.target.value)} />
              </div>
            </div>
            <div>
              <div>
                <input type="number" placeholder="몸무게" onChange={e => setKg(e.target.value)} />
              </div>
              <div>
                <div>
                  <Select ref={neuteredInput} options={neuteredOpt} onChange={setNeuteredCode} placeholder="중성화" />
                  <div>
                    {neuteredAlertOpen === 0 ? null : (
                      <div>
                        <Stack sx={{ width: '100%' }} spacing={2}>
                          <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                            중성화를 선택해주세요.
                          </Alert>
                        </Stack>
                      </div>
                    )}
                  </div>
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
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(0)} />
              <span>귀</span>
              <div className={styles.categoryIndexEar}>
                <Select
                  options={categoryEarOpt}
                  onChange={setCategoryEar}
                  defaultValue={categoryEarOpt[categoryEar.value]}
                />
              </div>
            </div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(1)} />
              <span>무늬</span>
              <div className={styles.categoryIndexPat}>
                <Select
                  options={categoryPatternOpt}
                  onChange={setCategoryPattern}
                  defaultValue={categoryPatternOpt[categoryPattern.value]}
                />
              </div>
            </div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} onClick={() => openModal(2)} />
              <span>꼬리</span>
              <div className={styles.categoryIndexTail}>
                <Select
                  options={categoryTailOpt}
                  onChange={setCategoryTail}
                  defaultValue={categoryTailOpt[categoryTail.value]}
                />
              </div>
            </div>
          </div>

          <div>
            <div>
              <img src={infoIcon} alt="" className={styles['info-icon']} style={{ visibility: 'hidden' }} />
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
              <img src={infoIcon} alt="" className={styles['info-icon']} style={{ visibility: 'hidden' }} />
              <span>털색</span>
              <div>
                {categoryPattern.value === 2 ? (
                  <input className={styles.colorPickerHalf} type="color" onChange={e => setColor1(e.target.value)} />
                ) : categoryPattern.value === 1 ? (
                  <input className={styles.colorPicker} type="color" onChange={e => setColor1(e.target.value)} />
                ) : categoryPattern.value === 3 ? (
                  <input className={styles.colorPickerThree} type="color" onChange={e => setColor1(e.target.value)} />
                ) : (
                  <input className={styles.colorPickerDisabled} type="color" disabled />
                )}
                {categoryPattern.value === 2 ? (
                  <input className={styles.colorPickerHalf} type="color" onChange={e => setColor2(e.target.value)} />
                ) : categoryPattern.value === 3 ? (
                  <input className={styles.colorPickerThree} type="color" onChange={e => setColor2(e.target.value)} />
                ) : null}
                {categoryPattern.value === 3 ? (
                  <input className={styles.colorPickerThree} type="color" onChange={e => setColor3(e.target.value)} />
                ) : null}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.subTitle}>실종/발견 장소</div>
      <hr />
      {positionAlertOpen === 0 ? null : (
        <div>
          <Stack sx={{ width: '100%' }} spacing={2}>
            <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
              장소를 선택해주세요.
            </Alert>
          </Stack>
        </div>
      )}
      <div className={styles.map} ref={positionInput}>
        <Map
          // 지도를 표시할 Container
          center={{
            // 지도의 중심좌표
            lat: position.lat,
            lng: position.lng,
          }}
          style={{
            width: '100%',
            height: '100%',
          }}
          level={13} // 지도의 확대 레벨
          onClick={(_t, mouseEvent) =>
            setPosition({
              lat: mouseEvent.latLng.getLat(),
              lng: mouseEvent.latLng.getLng(),
            })
          }
        >
          <Circle
            center={{ lat: position.lat, lng: position.lng }}
            radius={40000}
            strokeWeight={1}
            strokeColor="#ffd80b"
            strokeOpacity={0.1}
            strokeStyle="solid"
            fillColor="#ffd80b"
            fillOpacity={0.2}
          />
          {position && (
            <MapMarker
              position={position}
              image={{
                src: 'https://i.ibb.co/z42FXX4/002-2.png', // 마커이미지의 주소입니다
                size: {
                  width: 64,
                  height: 64,
                }, // 마커이미지의 크기입니다
                options: {
                  offset: {
                    x: 27,
                    y: 69,
                  }, // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                },
              }}
            />
          )}
        </Map>
      </div>

      <div className={styles.subTitle} ref={contentInput}>
        상세특징
      </div>
      <hr />
      <div>
        {contentAlertOpen === 0 ? null : (
          <div>
            <Stack sx={{ width: '100%' }} spacing={2}>
              <Alert severity="error" sx={{ fontSize: '15px', color: 'red' }}>
                상세특징을 입력해주세요.
              </Alert>
            </Stack>
          </div>
        )}
        <div className={styles.ckEditor}>
          <CKEditor
            editor={ClassicEditor}
            onReady={editor => {
              // You can store the "editor" and use when it is needed.
            }}
            config={{
              placeholder: '강아지 상세정보 / 신고자 연락처',
            }}
            data="<h3>실종견/임보견 신고 템플릿 예시</h3>
            <br>
            <h3>신고자/보호자 정보</h3>
            <p>이름 : </p>
            <p>연락처 : </p>
            <br>
            <h3>강아지 상세특징</h3>
            <p>강아지의 상세 특징을 적어주세요.</p>"
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
      {/* Modal  */}
      <DetailModal
        open={modal}
        close={closeModal}
        title={modalNum === 0 ? '귀 모양 상세' : modalNum === 1 ? '무늬 상세' : '꼬리 모양 상세'}
      >
        {modalNum === 0 ? <EarDetail /> : modalNum === 1 ? <PatternDetail /> : <TailDetail />}
      </DetailModal>
    </div>
  );
}
