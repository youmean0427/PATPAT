import React from 'react';
import Ear1 from 'assets/images/ear1.png';
import Ear2 from 'assets/images/ear2.png';
import Ear3 from 'assets/images/ear3.png';
import Ear4 from 'assets/images/ear4.png';
import Ear5 from 'assets/images/ear5.png';
import Ear6 from 'assets/images/ear6.png';
import Ear7 from 'assets/images/ear7.png';
import Ear8 from 'assets/images/ear8.png';
import styles from './ReportPersonalDogUpdateContent.module.scss';

export default function EarDetail() {
  return (
    <div className={styles['modal-content']}>
      <div className={styles['modal-detail']}>
        <p>
          1. <span>직립 귀</span> : 귀가 쫑긋 서 있고, 귀 끝이 둥글거나 뾰족한 귀
        </p>
        <img src={Ear1} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          2. <span>박쥐 귀</span> : 귀 사이 큰 V자형 공간이 있어 귀가 바깥으로 펼쳐진 귀
        </p>
        <img src={Ear2} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          3. <span>반직립 귀</span> : 귀 끝의 1/4 정도가 앞으로 구부러져 있는 귀
        </p>
        <img src={Ear3} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          4. <span>버튼 귀</span> : 귓볼이 반으로 접혀 귓구멍을 감춘 귀
        </p>
        <img src={Ear4} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          5. <span>장미 귀</span> : 귀가 뒤로 젖혀져 귀 끝이 옆으로 떨어진 귀
        </p>
        <img src={Ear5} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          6. <span>처진 귀</span> : 귀가 시작되는 머리 옆에서부터 그대로 아래로 축 처진 귀
        </p>
        <img src={Ear6} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          7. <span>접힌 귀</span> : 귀의 시작이 머리 윗부분이면서 아래로 축 처진 귀
        </p>
        <img src={Ear7} alt="" />
      </div>
      <div className={styles['modal-detail']}>
        <p>
          8. <span>V자 귀</span> : 앞에서 봤을 때 접힌 귀 모양이 V자인 귀
        </p>
        <img src={Ear8} alt="" />
      </div>
    </div>
  );
}
