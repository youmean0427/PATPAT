import { file } from '@babel/types';
import { QueryClient, useMutation, useQueryClient } from '@tanstack/react-query';
import { createReport } from 'apis/api/report';
import axios from 'axios';
import { missingDogList } from 'mocks/data/missing';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './ReportCreateContent.module.scss';

export default function ReportCreateContent() {
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [state, setState] = useState('0');
  // 위도,경도로 수정
  const [place, setPlace] = useState('0');

  const [gender, setGender] = useState('0');
  const [breedName, setBreedName] = useState('');
  const [weight, setWeight] = useState('');
  const [neutered, setNeutered] = useState('0');
  const [content, setContent] = useState('');
  const [categoryEar, setCategoryEar] = useState('');
  const [categoryColor, setCategoryColor] = useState('');
  const [categoryPattern, setCategoryPattern] = useState('');
  const [categoryTail, setCategoryTail] = useState('');
  const [categoryCloth, setCategoryCloth] = useState('');
  const [categoryClothColor, setCategoryClothColor] = useState('');
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

  //  ==============================

  // const data = {
  //   title: title,
  //   name: name,
  //   state: state,
  //   gender: gender,
  //   breedName: breedName,
  //   weight: weight,
  //   neutered: neutered,
  //   content: content,
  //   categoryEar: categoryEar,
  //   categoryColor: categoryColor,
  //   fileUrlList: showImages,
  // };

  let formData = new FormData();
  formData.append('title', title);

  for (let i = 0; i < 4; i++) {
    formData.append('fileUrlList', showImages[i]);
  }

  const { mutate: mutation } = useMutation(['missingDogList'], () => {
    return createReport(state, formData);
  });

  console.log('DATA: ', formData);

  return (
    <div className={styles.container}>
      {/* <form onSubmit={handleSubmit}> */}
      <form
        onSubmit={e => {
          e.preventDefault();
          mutation();
        }}
      >
        <div>
          <input type="text" placeholder="제목" onChange={e => setTitle(e.target.value)} />
        </div>
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
        <div>
          <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} />
          <input type="radio" value="0" checked={gender === '0'} onChange={e => setGender(e.target.value)} />남
          <input type="radio" value="1" checked={gender === '1'} onChange={e => setGender(e.target.value)} />여
        </div>
        <select name="" id="" onChange={e => setState(e.target.value)}>
          <option value="0">실종</option>
          <option value="1">임시보호</option>
          <option value="2">완료</option>
        </select>
        <div>
          <select name="" id="" onChange={e => setPlace(e.target.value)}>
            <option value="0">장소</option>
            <option value="1">장소</option>
            <option value="2">장소</option>
          </select>
        </div>
        <div>
          <select name="" id="" onChange={e => setBreedName(e.target.value)}>
            <option value="웰시코기">웰시코기</option>
            <option value="푸들">푸들</option>
            <option value="보더콜리">보더콜리</option>
            <option value="잉글리쉬쉽독">잉글리쉬쉽독</option>
          </select>
        </div>
        <div>
          <input type="number" placeholder="몸무게" onChange={e => setWeight(e.target.value)} />
        </div>
        <div>
          <input type="radio" value="0" checked={neutered === '0'} onChange={e => setNeutered(e.target.value)} />유
          <input type="radio" value="1" checked={neutered === '1'} onChange={e => setNeutered(e.target.value)} />무
          <input type="radio" value="2" checked={neutered === '2'} onChange={e => setNeutered(e.target.value)} />
          모름
        </div>
        <textarea onChange={e => setContent(e.target.value)}></textarea>

        <div>카테고리 등록 (선택)</div>
        <div>
          <select name="" id="" onChange={e => setCategoryEar(e.target.value)}>
            <option value="접힘">접힘</option>
            <option value="펴짐">펴짐</option>
            <option value="모름">모름</option>
          </select>
          <select name="" id="" onChange={e => setCategoryColor(e.target.value)}>
            <option value="흰색">흰색</option>
            <option value="검은색">검은색</option>
            <option value="모름">모름</option>
          </select>
          <select name="" id="" onChange={e => setCategoryPattern(e.target.value)}>
            <option value="흰색">흰색</option>
            <option value="검은색">검은색</option>
            <option value="모름">모름</option>
          </select>
          <select name="" id="" onChange={e => setCategoryTail(e.target.value)}>
            <option value="말림">말림</option>
            <option value="안말림">안말림</option>
            <option value="모름">모름</option>
          </select>
          <select name="" id="" onChange={e => setCategoryCloth(e.target.value)}>
            <option value="예">예</option>
            <option value="아니오">아니오</option>
          </select>
          <select name="" id="" onChange={e => setCategoryClothColor(e.target.value)}>
            <option value="빨강">빨강</option>
            <option value="파랑">파랑</option>
            <option value="노랑">노랑</option>
            <option value="모름">모름</option>
          </select>
        </div>
        <button type="submit">등록</button>
        <Link to="/report">
          <button>취소</button>
        </Link>
      </form>
    </div>
  );
}
