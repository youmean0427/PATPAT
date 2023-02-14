import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useEffect } from 'react';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import ModalFrame from '../ModalFrame';
import { updateShelter } from 'apis/api/shelter';
import styles from './EditInfoModal.module.scss';
import { encodeFileToBase64 } from 'utils/image';
import { AiFillCloseCircle } from 'react-icons/ai';
import { BsFillCameraFill } from 'react-icons/bs';
import { toast } from 'react-toastify';

const EditInfoModal = ({ isOpen, handleClickModalClose, data, shelterId }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    mode: 'onChange',
  });

  const queryClient = useQueryClient();

  const { mutate, isLoading } = useMutation(['updateShelter'], formData => updateShelter(formData), {
    onSuccess: data => {
      queryClient.invalidateQueries({ queryKey: ['getShelterDetailInfo', shelterId] });
      toast('성공', { type: 'success' });
      handleClickModalClose();
    },
    onError: error => {
      console.log(error);
    },
  });

  const [files, setFiles] = useState([]);
  const [Base64s, setBase64s] = useState([]);
  const [count, setCount] = useState(0);
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
    const formData = new FormData();
    formData.append('name', nData.name);
    formData.append('ownerName', nData.ownerName);
    formData.append('phoneNumber', nData.phoneNumber);
    formData.append('infoContent', nData.infoContent);
    formData.append('shelterId', shelterId);
    let count = 0;
    Array.from(files).forEach(item => {
      count++;
      if (count > LIMIT_UPLOAD_IMAGE_FILE) return;
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

  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose} width={700} height={600}>
      <div className={styles.title}>보호소 정보 수정</div>
      <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
        <div className={styles['input-box']}>
          <label>보호소 이름</label>
          <input
            className={styles.input}
            type="text"
            placeholder="보호소 이름"
            defaultValue={data?.name}
            {...register('name')}
          />
        </div>
        <div className={`${styles['input-box']} ${styles.ownerName}`}>
          <label>담당자 이름</label>
          <input
            className={styles.input}
            type="text"
            placeholder="담당자 이름"
            defaultValue={data?.ownerName}
            {...register('ownerName')}
          />
        </div>
        <div className={styles['input-box']}>
          <label>담당자 번호</label>
          <input
            className={styles.input}
            type="tel"
            placeholder="담당자 전화번호"
            defaultValue={data?.phoneNumber}
            {...register('phoneNumber', {
              required: {
                value: true,
                message: '전화번호를 입력해주세요.',
              },
            })}
          />
        </div>
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
        <div className={`${styles['input-box']} ${styles.info}`}>
          <label>소개글</label>
          <textarea
            className={`${styles.input} ${styles.info}`}
            type="textarea"
            placeholder="소개글"
            defaultValue={data?.infoContent}
            {...register('infoContent')}
          />
        </div>
        <button className={styles.button} type="submit">
          정보 수정
        </button>
      </form>
    </ModalFrame>
  );
};
export default EditInfoModal;
