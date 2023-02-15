import styles from './PersonalDogItem.module.scss';
import Card from 'components/Common/Card';
import React from 'react';
import { Link } from 'react-router-dom';
// import test from '../../../assets/images/ear8.png';

export default function PersonalDogItem({ item }) {
  const { personalProtectionId, title, content, thumbnail } = item;

  return (
    <div className={styles.card}>
      <Link to={`personal/${personalProtectionId}`} state={{ personalProtectionId }}>
        <Card>
          <div>
            {/* thumbnail로 변경 */}
            {title.includes('[완료]') === false ? (
              <img src={`${thumbnail.filePath}`} className={styles.img} alt="" />
            ) : (
              <img src={`${thumbnail.filePath}`} className={styles.disabled} alt="" />
            )}
            <div className={styles.description}>
              <div className={styles.title}>{title}</div>
              <div className={styles.content}>
                {content === null
                  ? null
                  : content
                      .replace(/(<([^>]+)>)/gi, ' ')
                      .replace(/&quot;/g, ' ')
                      .replace(/\"n/, ' ')
                      .replace(/&amp;/g, ' ')
                      .replace(/&nbsp;/g, ' ')}
              </div>
            </div>
          </div>
        </Card>
      </Link>
      <br />
    </div>
  );
}
