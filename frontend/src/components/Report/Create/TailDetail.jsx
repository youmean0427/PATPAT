import React from 'react';
import styles from './ReportCreateContent.module.scss';
import Tail1 from 'assets/images/Tail1.jpg';
import Tail2 from 'assets/images/Tail2.jpg';
import Tail3 from 'assets/images/Tail3.jpg';
import Tail4 from 'assets/images/Tail4.jpg';

export default function TailDetail() {
  return (
    <div className={styles['modal-content']}>
      <div className={styles['modal-detail']}>
        <p>
          1. <span>말린 꼬리</span> : 고리처럼 동그랗게 등 쪽으로 말려 있는 꼬리
        </p>
        <img src={Tail1} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          2. <span>수달 꼬리</span> : 크고 두꺼운 C자 모양으로 밑동은 두껍고, 끝으로 갈수록 가늘어지는 꼬리
        </p>
        <img src={Tail2} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          3. <span>당근 꼬리</span> : 수달꼬리와 비슷하게 밑동은 두껍고 끝은 가는 형태지만 더 가늘고 끝이 뾰족한 꼬리
        </p>
        <img src={Tail3} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          4. <span>단발 꼬리</span> : 아주 짧거나 거의 없는 형태의 꼬리
        </p>
        <img src={Tail4} alt="" />
      </div>
    </div>
  );
}
