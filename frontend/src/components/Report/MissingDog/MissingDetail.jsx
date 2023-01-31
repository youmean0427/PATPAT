import React from 'react';
import { useLocation } from 'react-router-dom';

export default function MissingDetail() {
  const location = useLocation();
  // console.log(location);
  const item = location.state.item;
  return (
    <div>
      <div>{item.title}</div>
      <img src={item.fileUrlList} alt="pic" />
      <div>{item.name}</div>
      <div>{item.gender}</div>
      <div>{item.neutered}</div>
      <div>{item.weight}</div>

      <div>{item.categoryEar}</div>
      <div>{item.categoryColor}</div>
      <div>{item.categoryPattern}</div>
      <div>{item.categoryTail}</div>
      <div>{item.categoryCloth}</div>
      <div>{item.categoryClothColor}</div>
      <div>{item.content}</div>
    </div>
  );
}
