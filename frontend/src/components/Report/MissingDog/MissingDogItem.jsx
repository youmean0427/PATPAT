import styles from './MissingDogItem.module.scss';
import Card from 'components/Common/Card';
import React from 'react';
import { Link } from 'react-router-dom';
// import test from '../../../assets/images/ear8.png';
import HtmlReactParser from 'html-react-parser';
import { useState } from 'react';
import test from '../../../assets/images/ear8.png';
export default function MissingDogItem({ item }) {
  const { missingId, title, content, thumbnail } = item;
  // console.log(item);

  return (
    <div className={styles.card}>
      <Link to={`missing/${missingId}`} state={{ missingId }}>
        <Card>
          {/* thumbnail로 변경 */}
          <img src={test} alt="" />
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
        </Card>
      </Link>
      <br />
    </div>
  );
}
