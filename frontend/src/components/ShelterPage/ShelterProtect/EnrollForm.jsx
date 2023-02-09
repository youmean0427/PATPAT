import React from 'react';
import { Controller, useForm } from 'react-hook-form';
import styles from './EnrollForm.module.scss';
import { BsPlusCircleDotted } from 'react-icons/bs';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { enrollProtectFormDataListState } from 'recoil/atoms/protect';
import { useLoaderData } from 'react-router-dom';
import Select from 'react-select';
import { changeBreedList } from 'utils/changeSelectTemplate';
import { useEffect } from 'react';
import { encodeFileToBase64 } from 'utils/image';
import { BsFillCameraFill } from 'react-icons/bs';
import { AiFillCloseCircle } from 'react-icons/ai';
import TextField from '@mui/material/TextField';
export default function EnrollForm() {
  const { handleSubmit, control, register } = useForm({ mode: onchange });
  const [count, setCount] = useState(0);
  const [files, setFiles] = useState([]);
  const [Base64s, setBase64s] = useState([]);
  const [inputForm, setInputForm] = useState([]);
  const breedList = useLoaderData();
  const [formDataList, setFormDataList] = useRecoilState(enrollProtectFormDataListState);
  const LIMIT_UPLOAD_IMAGE_FILE = 4;

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
    console.log(nData);
    // const formData = new FormData();
    // formData.append('name', nData.name);
    // formData.append('ownerName', nData.ownerName);
    // formData.append('phoneNumber', nData.phoneNumber);
    // formData.append('infoContent', nData.infoContent);
    // formData.append('shelterId', shelterId);
    // let count = 0;
    // Array.from(files).forEach(item => {
    //   count++;
    //   if (count > LIMIT_UPLOAD_IMAGE_FILE) return;
    //   formData.append('uploadFile', item);
    // });
    // mutate(formData);
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
  const customStyles = {
    control: base => ({
      ...base,
      height: 35,
      minHeight: 35,
    }),
  };
  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <span className={styles['form-number']}>1</span>
        <BsPlusCircleDotted onClick={handleClickNewForm} className={styles.add} />
      </div>
      <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <div className={styles['form-left']}></div>
        <div className={styles['form-right']}>
          <div className={`${styles['input-item']} ${styles.name}`}>
            <label>이름</label>
            <input type="text" placeholder="이름을 입력해주세요" {...register('protectName')} />
          </div>
          <div className={`${styles['input-item']} ${styles.breed}`}>
            <label>견종</label>
            <Controller
              name="breed"
              render={({ field }) => (
                <Select {...field} placeholder="선택해주세요" options={changeBreedList(breedList)} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles.kg}`}>
            <label>몸무게</label>
            <input type="number" step="0.1" placeholder="몸무게" {...register('kg')} />
          </div>
          <div className={`${styles['input-item']} ${styles.age}`}>
            <label>추정 나이</label>
            <input type="number" placeholder="나이" {...register('age')} />
          </div>
          <div className={`${styles['input-item']} ${styles.gender}`}>
            <label>성별</label>
            <Controller
              name="gender"
              render={({ field }) => (
                <Select {...field} placeholder="선택해주세요" options={changeBreedList(breedList)} />
              )}
              control={control}
            />
          </div>
          <div className={`${styles['input-item']} ${styles.neutered}`}>
            <label>중성화</label>
            <Controller
              name="neutered"
              render={({ field }) => (
                <Select {...field} placeholder="선택해주세요" options={changeBreedList(breedList)} />
              )}
              control={control}
            />
          </div>
        </div>
        <button className={styles.enroll} type="submit">
          제출
        </button>
      </form>
    </div>
  );
}
