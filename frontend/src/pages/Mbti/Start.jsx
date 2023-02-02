import React from 'react';
import styles from './Start.module.scss';
import logo from 'assets/images/mbti-logo.png';
import { Link } from 'react-router-dom';
import MbtiContainer from 'components/Common/MbtiContainer';
export default function Start() {
  return (
    <MbtiContainer>
      <img className={styles.logo} src={logo} alt="mbti" />
      <div className={styles.bottom}>
        <p className={styles.desc}>
          <span>보호소</span>에 있는 강아지 중 나와 맞는 강아지를 만나보고 좋은 가족을 만날 수 있도록 응원해주세요.
        </p>
        <Link to="/mbti/test" className={styles['start-btn']}>
          테스트 시작하기
        </Link>
        <p className={styles['total-desc']}>
          현재 까지 총 <span>11,211명</span>이 테스트에 참여했어요!{' '}
        </p>
      </div>
    </MbtiContainer>
  );
}
