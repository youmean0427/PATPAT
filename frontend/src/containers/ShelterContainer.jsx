import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { checkMyShelter } from 'utils/checkMyShelter';
import { SiAddthis } from 'react-icons/si';
import styles from './ShelterContainer.module.scss';
export default function ShelterContainer({ title, children }) {
  const myShelterId = useRecoilValue(myShelterIdState);
  const { shelterId } = useParams();
  const navigate = useNavigate();
  return (
    <div className={styles.container}>
      <div className={styles.title}>
        <div className={styles['title-inner']}>
          {title}
          {checkMyShelter(shelterId, myShelterId) && (
            <button onClick={() => navigate(`/shelter/${shelterId}/protect/enroll`)}>
              <SiAddthis size={40} className={styles.btn} />
            </button>
          )}
        </div>
      </div>
      {children}
    </div>
  );
}
