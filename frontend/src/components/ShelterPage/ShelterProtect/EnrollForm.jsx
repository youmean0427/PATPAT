import React from 'react';
import { Controller, useForm } from 'react-hook-form';
import styles from './EnrollForm.module.scss';
import { BsPlusCircleDotted } from 'react-icons/bs';
import { useState } from 'react';
import { useRecoilState, useRecoilValue } from 'recoil';
import { enrollProtectFormDataListState } from 'recoil/atoms/protect';
import { useLoaderData } from 'react-router-dom';
import Select from 'react-select';
import { changeBreedList } from 'utils/changeSelectTemplate';
import { useEffect } from 'react';
import { encodeFileToBase64 } from 'utils/image';
import { BsFillCameraFill } from 'react-icons/bs';
import { AiFillCloseCircle } from 'react-icons/ai';
import TextField from '@mui/material/TextField';
import {
  categoryClothOpt,
  categoryEarOpt,
  categoryPatternOpt,
  categoryTailOpt,
  genderOpt,
  neuteredOpt,
} from 'data/shelterProtect';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { createProtect } from 'apis/api/protect';
import { myShelterIdState } from 'recoil/atoms/user';
export default function EnrollForm() {
  const { handleSubmit, control, register } = useForm({ mode: onchange });
  const [count, setCount] = useState(0);
  const [files, setFiles] = useState([]);
  const [Base64s, setBase64s] = useState([]);
  const [inputForm, setInputForm] = useState([]);
  const breedList = useLoaderData();
  const [formDataList, setFormDataList] = useRecoilState(enrollProtectFormDataListState);
  const LIMIT_UPLOAD_IMAGE_FILE = 4;
  const queryClient = useQueryClient();
  const myShelterId = useRecoilValue(myShelterIdState);
  const { mutate } = useMutation(['enrollShelterProtect'], formdata => createProtect(formdata), {
    onSuccess: data => {
      console.log(data);
      alert('등록되었습니다.');
    },
  });

  useEffect(() => {
    if (files) {
      console.log(files);
      let count = 0;
      setBase64s([]);
      Array.from(files).forEach(image => {
        count++;
        if (count > LIMIT_UPLOAD_IMAGE_FILE) return;
        encodeFileToBase64(image).then(data => setBase64s(prev => [...prev, { image: image, url: data }]));
      });
      setCount(files.length > LIMIT_UPLOAD_IMAGE_FILE ? LIMIT_UPLOAD_IMAGE_FILE : files.length);
    }
  }, [files]);

  const onSubmit = nData => {
    const {
      protectName,
      breed,
      age,
      kg,
      neutered,
      gender,
      categoryEar,
      categoryTail,
      categoryPattern,
      categoryColor,
      categoryCloth,
      uploadImages,
    } = nData;
    console.log(nData);
    const categoryColors = [];
    categoryColors.push(categoryColor);
    const formData = new FormData();
    formData.append('protectName', protectName);
    formData.append('breedId', breed.value);
    formData.append('genderCode', gender.value);
    formData.append('kg', kg);
    formData.append('neuteredCode', neutered.value);
    formData.append('age', age);
    formData.append('categoryEarCode', categoryEar.value);
    formData.append('categoryTailCode', categoryTail.value);
    formData.append('categoryPatternCode', categoryPattern.value);
    formData.append('categoryColor', categoryColors);
    formData.append('categoryClothCode', categoryCloth.value);
    formData.append('shelterId', myShelterId);
    Array.from(uploadImages).forEach(item => {
      formData.append('uploadFile', item);
    });
    mutate(formData);
  };

  const handleImagePreview = e => {
    const fileArr = e.target.files;
    setFiles(fileArr);
  };

  const handleClickDelete = e => {
    const deleteIndex = e.target.parentNode.parentNode.dataset.index;
    const dataTranster = new DataTransfer();
    for (let i = 0; i < files.length; i++) {
      if (i !== parseInt(deleteIndex)) {
        dataTranster.items.add(files[i]);
      }
    }
    setFiles(dataTranster.files);
  };

  const handleClickNewForm = () => {};

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <span className={styles['form-number']}>1</span>
        <BsPlusCircleDotted onClick={handleClickNewForm} className={styles.add} />
      </div>
      <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <div className={styles['form-left']}>
          <div className={styles['file-box']}>
            <label className={styles['file-label']} htmlFor="upload-file">
              <BsFillCameraFill className={styles.camera} />
              <span>{count}/4</span>
            </label>
            <input
              style={{ display: 'none' }}
              id="upload-file"
              type="file"
              accept="image/*"
              multiple
              {...register('uploadImages', { onChange: handleImagePreview })}
            />
            <div className={styles.preview}>
              {Base64s.map((item, index) => (
                <div
                  style={{ background: `no-repeat center/100% url("${item.url}")`, backgroundSize: 'cover' }}
                  className={styles['preview-item']}
                  key={index}
                  data-index={index}
                >
                  <AiFillCloseCircle onClick={handleClickDelete} className={styles.close} />
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className={styles['form-right']}>
          <div className={`${styles['input-item']} ${styles.name}`}>
            <label>이름</label>
            <input
              className={styles.input}
              type="text"
              placeholder="이름을 입력해주세요"
              {...register('protectName')}
            />
          </div>
          <div className={`${styles['input-item']} ${styles.breed}`}>
            <label>견종</label>
            <Controller
              name="breed"
              render={({ field }) => (
                <Select
                  {...field}
                  className={styles.select}
                  placeholder="선택해주세요"
                  options={changeBreedList(breedList)}
                />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles.kg}`}>
            <label>몸무게</label>
            <input className={styles.input} type="number" step="0.1" placeholder="몸무게" {...register('kg')} />
          </div>
          <div className={`${styles['input-item']} ${styles.age}`}>
            <label>추정 나이</label>
            <input className={styles.input} type="number" placeholder="나이" {...register('age')} />
          </div>
          <div className={`${styles['input-item']} ${styles.gender}`}>
            <label>성별</label>
            <Controller
              name="gender"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={genderOpt} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles.neutered}`}>
            <label>중성화</label>
            <Controller
              name="neutered"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={neuteredOpt} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles['category-ear']}`}>
            <label>귀</label>
            <Controller
              name="categoryEar"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={categoryEarOpt} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles['category-tail']}`}>
            <label>꼬리</label>
            <Controller
              name="categoryTail"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={categoryTailOpt} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles['category-pattern']}`}>
            <label>무늬</label>
            <Controller
              name="categoryPattern"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={categoryPatternOpt} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles['category-color']}`}>
            <label>털색</label>
            <input className={styles['color-input']} type="color" {...register('categoryColor')} />
          </div>
          <div className={`${styles['input-item']} ${styles['category-cloth']}`}>
            <label>옷착용</label>
            <Controller
              name="categoryCloth"
              render={({ field }) => (
                <Select className={styles.select} {...field} placeholder="선택해주세요" options={categoryClothOpt} />
              )}
              control={control}
            />
          </div>
          <div className={styles.enroll}>
            <button className={styles['enroll-btn']} type="submit">
              등록
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}
