import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';
import React from 'react';
import { Link } from 'react-router-dom';
// import test from '../../../assets/images/ear8.png';
export default function MissingDogItem({ item }) {
  const { missingId, title, content, thumbnail, stateCode } = item;
  // console.log(thumbnail.filePath);

  return (
    <div className={styles.card}>
      <Link to={`missing/${missingId}`} state={{ missingId }}>
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
