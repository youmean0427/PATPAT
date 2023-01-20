import React from 'react';
import styles from './VisionContent.module.scss';
export default function VisionContent() {
  return (
    <div className={styles.container}>
      <div className={styles['container-inner']}>
        <div className={styles.content}>
          <div>
            <span className={styles['content-blue']}>PATPAT</span>은 인간에 의해
            관리되는 모든 동물들이 인도적인 대우를 받게 하고자 하며, 더 나아가
            인간에 의해 이용되거나, 삶의 터전을 잃어가는 동물의 수(數)와
            종(種)을 줄여나감으로써, 인간과 동물이 생태적 · 윤리적 조화를 이루며
            살아가는 것을 목표로 합니다.
          </div>
          <br />
          <div>
            <div>핵심 가치</div>
            <div>
              <div>동물을 생명체로 존중하는 마음</div>
              <div>문화로 정착되는 동물보호</div>
              <div>동물의 대변자</div>
              <div>협력과 성실</div>
            </div>
          </div>
          <div></div>
          <div></div>
        </div>
      </div>
    </div>
  );
}
