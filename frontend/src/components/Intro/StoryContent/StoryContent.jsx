import React from 'react';
import styles from './StoryContent.module.scss';
import test1 from '../../../assets/images/test1.jpg';
import titleline from '../../../assets/images/titleline.png';

export default function StoryContent() {
  return (
    <div className={styles.container}>
      <div className={styles['container-inner']}>
        <div className={styles.content}>
          <div>2023년</div>
          <br />

          <div className={styles['content-container']}>
            <div>
              <img src={test1} alt="test1" />
              <img src={titleline} alt="titleline" />
              <div>동물카페에서 일어난 일어난</div>
            </div>
            <div>
              <img src={test1} alt="test1" />
              <img src={titleline} alt="titleline" />
            </div>

            <div>
              <img src={test1} alt="test1" />
              <img src={titleline} alt="titleline" />
            </div>
            <div>
              <img src={test1} alt="test1" />
              <img src={titleline} alt="titleline" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
