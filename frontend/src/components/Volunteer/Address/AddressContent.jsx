import SidoContent from './SidoContent';
import React from 'react';
import GugunContent from './GugunContent';
import ShelterContent from './ShelterContent';
import styles from './AddressContent.module.scss';

export default function AddressContent() {
  const [getSido, setGetSido] = React.useState('');
  const [getGugun, setGetGugun] = React.useState('');

  const getSidoFunc = React.useCallback(e => {
    setGetSido(e);
    setGetGugun('');
  }, []);
  // console.log(option);
  const getGugunFunc = React.useCallback(e => {
    setGetGugun(e);
  }, []);

  // 트랜지션 넣기
  return (
    <div className={styles.container}>
      <div>
        <SidoContent getSido={getSido} getSidoFunc={getSidoFunc} />
      </div>
      <div>
        <GugunContent sidoCode={getSido} getGugun={getGugun} getGugunFunc={getGugunFunc} />
      </div>
      <div>
        <ShelterContent gugunCode={getGugun} />
      </div>
    </div>
  );
}
