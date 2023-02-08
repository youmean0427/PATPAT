import Card from 'components/Common/Card';
import React from 'react';
import styles from './MissingDogItem.module.scss';
import { Link } from 'react-router-dom';
import test from '../../../assets/images/ear8.png';
import HtmlReactParser from 'html-react-parser';
export default function MissingDogItem({ item }) {
  const { missingId, title, content } = item;
  // console.log(item);
  return (
    <div className={styles.card}>
      <Link to={`missing/${missingId}`} state={{ missingId }}>
        <Card>
          {/* thumbnail로 변경 */}
          <img src={test} alt="" />
          <div className={styles.description}>
            <div className={styles.title}>{title}</div>
            {/* HTMl null 값 못받음 */}
            <div className={styles.content}>{HtmlReactParser('')}</div>
          </div>
        </Card>
      </Link>
      <br />
    </div>
  );
}
