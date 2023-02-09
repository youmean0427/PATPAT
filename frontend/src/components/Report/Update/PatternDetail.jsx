import React from 'react';
import styles from './ReportUpdateContent.module.scss';
import Pattern1 from 'assets/images/Pattern1.jpg';
import Pattern2 from 'assets/images/Pattern2.jpg';
import Pattern3 from 'assets/images/Pattern3.jpg';
import Pattern4 from 'assets/images/Pattern4.jpg';
import Pattern5 from 'assets/images/Pattern5.jpg';
import Pattern6 from 'assets/images/Pattern6.jpeg';
import Pattern7 from 'assets/images/Pattern7.jfif';
import Pattern8 from 'assets/images/Pattern8.jfif';
import Pattern9 from 'assets/images/Pattern9.jpg';
import Pattern10 from 'assets/images/Pattern10.png';

export default function PatternDetail() {
  return (
    <div className={styles['modal-content']}>
      <div className={styles['modal-detail']}>
        <p>
          1. <span>솔리드</span> : 단색 패턴
        </p>
        <img src={Pattern1} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          2. <span>바이컬러</span> : 흰 색상을 포함한 두가지 색으로 구성된 패턴
        </p>
        <img src={Pattern2} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          3. <span>트라이컬러</span> : 아래쪽은 흰색 계열이면서 전체적으로 세가지 색 이상으로 구성된 패턴
        </p>
        <img src={Pattern3} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          4. <span>탄</span> : 몸통 부분의 경우가 어두운색이며, 몸통 밑 부분이 황갈색이면서 주둥이 또는 눈썹과 같은 곳에
          밝은 색상으로 구성된 패턴
        </p>
        <img src={Pattern4} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          5. <span>턱시도</span> : 전체적으로 검정색이면서 가슴과 턱, 발에 흰색으로 구성된 패턴
        </p>
        <img src={Pattern5} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          6. <span>할리퀸 / 스팟</span> : 흰색 바탕에 모양, 크기가 제각각인 얼룩무늬로 구성된 패턴
        </p>
        <img src={Pattern6} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          7. <span>브린들</span> : 전반적으로 두가지 색으로 이루어진 호랑이 줄무늬 패턴
        </p>
        <img src={Pattern7} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          8. <span>새들</span> : 등쪽만 검정색으로 이루어진 패턴
        </p>
        <img src={Pattern8} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          9. <span>세이블</span> : 금, 은, 회색 또는 황갈색 배경에 끝이 검정색 털로 구성된 패턴
        </p>
        <img src={Pattern9} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          10. <span>멀</span> : 대리석과 유사한 모양으로 어두운 색의 점이 군데군데 위치한 패턴
        </p>
        <img src={Pattern10} alt="" />
      </div>
    </div>
  );
}
