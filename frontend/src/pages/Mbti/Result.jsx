import { useQuery } from '@tanstack/react-query';
import { getMbtiResultAPI } from 'apis/MbtiApi';
import React from 'react';
import { useLocation, useParams } from 'react-router';

export default function Result() {
  const { state } = useLocation();
  console.log(state);
  const { data, isLoading } = useQuery(['mbti-result'], () => getMbtiResultAPI(state.mbti));
  if (isLoading) return;
  console.log(data);
  const { id, mbti, kind, imgUrl } = data;
  return (
    <div>
      <img src={imgUrl} alt="img" />
      {mbti},{kind}
    </div>
  );
}
