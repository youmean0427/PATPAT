import { useQuery } from '@tanstack/react-query';
import { getBoardDetail } from 'apis/api/board';
import React from 'react';
import ReviewFrame from './ReviewFrame';
import styles from './ReviewModal.module.scss';
import ImageSlide from 'components/ShelterPage/ImageSlide';
export default function ReviewModal({ isOpen, handleClickModalClose, boardId }) {
  const { data, isLoading } = useQuery(['getBoardDetail', boardId], () => getBoardDetail(boardId));
  if (isLoading) return;
  console.log(data);
  return (
    <ReviewFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose}>
      <ImageSlide imageList={data.fileUrlList} />
    </ReviewFrame>
  );
}
