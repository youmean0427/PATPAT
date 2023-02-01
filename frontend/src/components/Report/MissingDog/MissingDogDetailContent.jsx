import React from 'react';
import styles from './MissingDogDetailContent.module.scss';
import { useQuery } from '@tanstack/react-query';
import { getMissingDogDetail } from 'apis/api/report';
import { Link } from 'react-router-dom';
export default function MissingDogDetailContent({ item }) {
  console.log('1마리', item);
  // if (isLoading) return;

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogDetail'],
    queryFn: () => getMissingDogDetail(item),
  });

  if (isLoading) return;

  return (
    <div>
      <div className={styles.container}>
        <div className={styles.title}>{data.title}</div>
        <div>
          <div>
            <img className={styles.thumbnail} src={data.fileUrlList[0]} alt="pic" />
          </div>
          <div className={styles['container-picture']}>
            <img src={data.fileUrlList[1]} alt="pic" />
            <img src={data.fileUrlList[2]} alt="pic" />
          </div>
        </div>
        <div className={styles['container-content']}>
          <div>
            <div className={styles['container-content-inner']}>
              <div>이름</div>
              <div>{data.name}</div>
            </div>
            <div className={styles['container-content-inner']}>
              <div>성별</div>
              <div>{data.gender}</div>
            </div>
            <div className={styles['container-content-inner']}>
              <div>몸무게</div>
              <div>{data.weight}</div>
            </div>
            <div className={styles['container-content-inner']}>
              <div>중성화</div>
              <div>{data.neutered}</div>
            </div>
          </div>
        </div>
        <div className={styles['container-character']}>
          <div>
            <div>귀</div>
            <div>털색</div>
            <div>무늬색</div>
          </div>
          <div>
            <div>{data.categoryEar}</div>
            <div>{data.categoryColor}</div>
            <div>{data.categoryPattern}</div>
          </div>
          <div>
            <div>꼬리</div>
            <div>옷착용</div>
            <div>옷색</div>
          </div>
          <div>
            <div>{data.categoryTail}</div>
            <div>{data.categoryCloth}</div>
            <div>{data.categoryClothColor}</div>
          </div>
        </div>

        <div className={styles.content}>{data.content}</div>

        <div className={styles['container-button']}>
          <button>이전</button>
          <Link to="/report">
            <button>목록</button>
          </Link>
          <button>다음</button>
        </div>
      </div>
    </div>
  );
}
