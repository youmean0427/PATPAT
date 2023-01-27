import MbtiContainer from 'components/Common/MbtiContainer';
import React from 'react';
import styles from './Test.module.scss';
import { testContents as tests } from 'data/test';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Loading from 'components/Common/Loading';
import logo from 'assets/images/mbti-logo.png';
export default function Test() {
  const [q, setQ] = useState(0);
  const [userAns, setUserAns] = useState([]);
  const navigate = useNavigate();

  const handleClick = e => {
    const value = e.currentTarget.value;
    setUserAns(prev => [...prev, value]);
    setQ(prev => prev + 1);
  };

  const handleMoveToResult = () => {
    const { result } = userAns.reduce(
      (acc, ans, index) => {
        acc[ans] = ++acc[ans];
        if (index === userAns.length - 1) {
          if (acc.i >= acc.e) acc.result = acc.result.concat('i');
          else acc.result = acc.result.concat('e');
          if (acc.n >= acc.s) acc.result = acc.result.concat('n');
          else acc.result = acc.result.concat('s');

          if (acc.t >= acc.f) acc.result = acc.result.concat('t');
          else acc.result = acc.result.concat('f');

          if (acc.p >= acc.j) acc.result = acc.result.concat('p');
          else acc.result = acc.result.concat('j');
        }
        return acc;
      },
      { i: 0, e: 0, n: 0, s: 0, t: 0, f: 0, p: 0, j: 0, result: '' }
    );
    setTimeout(() => {
      // 계산하는 것처럼 보이게 하기 위해 넣은 timeout
      navigate(`/mbti/result/${result}`);
    }, Math.floor(Math.random() * 1000 + 2000));
    return <Loading />;
  };

  if (!tests[q]) return handleMoveToResult();
  return (
    <MbtiContainer>
      <img className={styles.logo} src={logo} alt="logo" />
      <div className={styles.statusArea}>
        <span>Question {q + 1}/12</span>
        <div className={styles.statusBar}>
          <div className={styles.status} style={{ width: `${Math.round((q / tests.length) * 100)}%` }} />
        </div>
      </div>
      <div className={styles.testParagraphArea}>
        <p>{tests[q].question}</p>
        <button onClick={handleClick} value={tests[q].selection[0].value}>
          {tests[q].selection[0].answer}
        </button>
        <button onClick={handleClick} value={tests[q].selection[1].value}>
          {tests[q].selection[1].answer}
        </button>
      </div>
    </MbtiContainer>
  );
}
