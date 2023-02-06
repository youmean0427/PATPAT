import React from 'react';
import styles from './DogDetailContent.module.scss';
import { useQuery } from '@tanstack/react-query';
import { getMissingDogDetail } from 'apis/api/report';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';
export default function DogDetailContent({ item, state }) {
  const { isLoading, data } = useQuery({
    queryKey: ['missingDogDetail'],
    queryFn: () => getMissingDogDetail(item),
  });

  if (isLoading) return;

  return (
    <div>
      <header className={styles['container-title']}>
        <div className={styles.title}>{data.title}</div>
        <div>
          <div className={styles['container-title-inner']}>
            <div className={styles['container-title-inner-user']}>
              <span className={styles.writer}>{data.userId}</span>
              <span className={styles.date}>23.02.03</span>
            </div>
            <div>
              <Link to="update" state={{ data, state }}>
                <Button variant="contained" className={styles.button}>
                  수정
                </Button>
              </Link>
            </div>
          </div>
        </div>
        <hr />
      </header>
      <div className={styles.container}>
        <div className={styles['container-inner']}>
          <div className={styles['container-picture']}>
            <div className={styles.thumbnail}>
              <img src={data.fileUrlList[0]} alt="" />
            </div>
            <div className={styles['container-subpicture']}>
              <div className={styles['container-subpicture-inner']}>
                <div>
                  <img src={data.fileUrlList[1]} alt="" />
                </div>
                <div>
                  <img src={data.fileUrlList[2]} alt="" />
                </div>
              </div>
            </div>
          </div>

          <div className={styles['container-content']}>
            <div className={styles['container-content-title']}>
              <div className={styles.name}>{data.name}</div>
              {state === 0 ? <div className={styles.stateButtonRed}>실종</div> : null}
              {state === 1 ? <div className={styles.stateButtonOrange}>임시보호</div> : null}
            </div>
            <hr />
            <div className={styles['container-content-info']}>
              <div className={styles['container-content-info-inner']}>
                <div className={styles['container-content-info-inner-list']}>
                  <div>품종</div>
                  <div>성별</div>
                  <div>실종/발견 장소</div>
                  <div>몸무게</div>
                  <div>중성화</div>
                </div>

                <div className={styles['container-content-info-inner-content']}>
                  <div>{data.breedId}</div>
                  <div>{data.genderCode}</div>
                  <div>장소</div>
                  <div>{data.kg}</div>
                  <div>{data.neutered}</div>
                </div>
              </div>
            </div>
            <hr />
            <div className={styles['container-content-character']}>
              <div className={styles['container-content-character-list']}>
                <div>귀</div>
                <div>털색</div>
                <div>무늬색</div>
              </div>
              <div className={styles['container-content-character-content']}>
                <div>{data.categoryEar}</div>
                <div>{data.categoryColor}</div>
                <div>{data.categoryPattern}</div>
              </div>
              <div className={styles['container-content-character-list']}>
                <div>꼬리</div>
                <div>옷착용</div>
                <div>옷색</div>
              </div>
              <div className={styles['container-content-character-content']}>
                <div>{data.categoryTail}</div>
                <div>{data.categoryCloth}</div>
                <div>{data.categoryClothColor}</div>
              </div>
            </div>
          </div>
        </div>
        <div className={styles.content}>
          <div>{data.content}</div>
        </div>
      </div>
    </div>
  );
}
