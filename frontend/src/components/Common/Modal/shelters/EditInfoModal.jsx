import { useMutation, useQueryClient } from '@tanstack/react-query';
import React from 'react';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import ModalFrame from '../ModalFrame';
import Images from './Images';
import { updateShelter } from 'apis/api/shelter';
import styles from './EditInfoModal.module.scss';

const EditInfoModal = ({ isOpen, handleClickModalClose, data, shelterId }) => {
  const {
    register,
    handleSubmit,
    clearErrors,
    setError,
    setValue,
    formState: { errors },
  } = useForm({
    mode: 'onChange',
  });
  const queryClient = useQueryClient();

  const { mutate, isLoading } = useMutation(['updateShelter'], formData => updateShelter(formData), {
    onSuccess: data => {
      queryClient.invalidateQueries({ queryKey: ['getShelterDetailInfo', shelterId] });
      handleClickModalClose();
    },
    onError: error => {
      console.log(error);
    },
  });

  const [previewImages, setPreviewImages] = useState(() => {
    return data.imageList.map(item => item.filePath);
  });

  const onSubmit = data => {
    const formData = new FormData();

    formData.append('name', data.name);
    formData.append('ownerName', data.ownerName);
    formData.append('phoneNumber', data.phoneNumber);
    Array.from(data.uploadImages).forEach(item => {
      formData.append('uploadFile', item);
    });
    formData.append('infoContent', data.infoContent);
    formData.append('shelterId', shelterId);

    mutate(formData);
  };

  const handleImagePreview = e => {
    const fileArr = e.target.files;
    console.log(fileArr);
    let fileURLs = [];
    let file;
    let filesLength = fileArr.length > 6 ? 6 : fileArr.length;
    for (let i = 0; i < filesLength; i++) {
      file = fileArr[i];
      let reader = new FileReader();
      reader.onload = () => {
        fileURLs[i] = reader.result;
        setPreviewImages([...fileURLs]);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className={styles['file-box']}>
          <label className={styles['file-label']} htmlFor="upload-file">
            이미지
          </label>
          <input
            style={{ display: 'none' }}
            id="upload-file"
            type="file"
            accept="image/*"
            multiple
            {...register('uploadImages', { onChange: handleImagePreview })}
          />
        </div>
        <div>
          <input type="text" placeholder="보호소 이름" defaultValue={data?.name} {...register('name')} />
          <div>{errors.name && <span>{errors.name.message}</span>}</div>
        </div>
        <div>
          <input type="text" placeholder="담당자 이름" defaultValue={data?.ownerName} {...register('ownerName')} />
          <div>{errors.ownerName && <span>{errors.ownerName.message}</span>}</div>
        </div>
        <div>
          <input
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
          <div>{errors.phoneNumber && <span>{errors.phoneNumber.message}</span>}</div>
        </div>
        <div>
          <input type="textarea" placeholder="소개글" defaultValue={data?.infoContent} {...register('infoContent')} />
          <div>{errors.infoContent && <span>{errors.infoContent.message}</span>}</div>
        </div>
        {previewImages.length > 0 && (
          <div className={styles['preview-images']}>
            <Images previewImages={previewImages} />
          </div>
        )}
        <button type="submit">정보 수정</button>
      </form>
    </ModalFrame>
  );
};
export default EditInfoModal;
