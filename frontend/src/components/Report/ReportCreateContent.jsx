import React, { useState } from 'react';
import styles from './ReportCreateContent.module.scss';
export default function ReportCreateContent() {
  const [title, setTitle] = useState('');
  const [name, setName] = useState('');
  const [state, setState] = useState('0');
  const [gender, setGender] = useState('0');
  const [weight, setWeight] = useState('');
  const [neutered, setNeutered] = useState('0');
  const [content, setContent] = useState('');
  const [categoryEar, setCategoryEar] = useState('');
  const [categoryColor, setCategoryColor] = useState('');
  const [categoryPattern, setCategoryPattern] = useState('');
  const [categoryTail, setCategoryTail] = useState('');
  const [categoryCloth, setCategoryCloth] = useState('');
  const [categoryClothColor, setCategoryClothColor] = useState('');

  const handleGenderChange = event => {
    setGender(event.target.value);
  };
  const handleNeuteredChange = event => {
    setNeutered(event.target.value);
  };
  console.log(title, name, state, gender, weight, neutered, content);
  return (
    <div className={styles.container}>
      <form action="">
        <div>
          <input type="text" placeholder="제목" onChange={e => setTitle(e.target.value)} />
        </div>
        <div>
          <input type="text" placeholder="이름" onChange={e => setName(e.target.value)} />
        </div>
        <select name="" id="" onChange={e => setState(e.target.value)}>
          <option value="0">실종</option>
          <option value="1">임시보호</option>
          <option value="2">완료</option>
        </select>
        <div>
          <input type="radio" value="0" checked={gender === '0'} onChange={handleGenderChange} />남
          <input type="radio" value="1" checked={gender === '1'} onChange={handleGenderChange} />여
        </div>
        <div>
          <input type="number" placeholder="몸무게" onChange={e => setWeight(e.target.value)} />
        </div>
        <div>
          <input type="radio" value="0" checked={neutered === '0'} onChange={handleNeuteredChange} />유
          <input type="radio" value="1" checked={neutered === '1'} onChange={handleNeuteredChange} />무
          <input type="radio" value="2" checked={neutered === '2'} onChange={handleNeuteredChange} />
          모름
        </div>
        <textarea onChange={e => setContent(e.target.value)}></textarea>
      </form>

      <div>카테고리 등록 (선택)</div>
      <div>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
        <select name="" id="">
          <option value=""></option>
          <option value=""></option>
        </select>
      </div>

      <button>등록</button>
      <button>취소</button>
    </div>
  );
}
