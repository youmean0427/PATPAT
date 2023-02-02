import React from 'react';
import styles from './MissingDogDetailContent.module.scss';
import { useQuery } from '@tanstack/react-query';
import { getMissingDogDetail } from 'apis/api/report';
import { Link } from 'react-router-dom';
export default function MissingDogDetailContent({ item }) {
  // console.log('id', item);

  const { isLoading, data } = useQuery({
    queryKey: ['missingDogDetail'],
    queryFn: () => getMissingDogDetail(item),
  });

  if (isLoading) return;

  return (
    <div>
      <div className={styles.container}>
        <div className={styles.title}>{data.title}</div>
        <div className={styles['container-inner']}>
          <div className={styles['container-picture']}>
            <div className={styles.thumbnail}>
              <img src={data.fileUrlList[0]} alt="pic" />
            </div>
            <div className={styles['container-subpicture']}>
              <div className={styles['container-subpicture-inner']}>
                <div>
                  <img src={data.fileUrlList[1]} alt="pic" />
                </div>
                <div>
                  <img src={data.fileUrlList[2]} alt="pic" />
                </div>
              </div>
            </div>
          </div>

          <div className={styles['container-content']}>
            <div className={styles['container-content-info']}>
              <div className={styles['container-content-info-inner']}>
                <div className={styles['container-content-info-inner-list']}>
                  <div>이름</div>
                  <div>성별</div>
                  <div>실종/발견 장소</div>
                  <div>몸무게</div>
                  <div>중성화</div>
                </div>

                <div className={styles['container-content-info-inner-content']}>
                  <div>{data.name}</div>
                  <div>{data.gender}</div>
                  <div>장소</div>
                  <div>{data.weight}</div>
                  <div>{data.neutered}</div>
                </div>
              </div>
            </div>
            <div className={styles['container-content-character']}>
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
