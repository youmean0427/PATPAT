import React from 'react';
import styles from './BoardsItem.module.scss';

export default function ReviewItem({ item }) {
  const { boardId, title, author, registDate, count, typeCode } = item;
  console.log(item);
  return <div>테이블</div>;
}
