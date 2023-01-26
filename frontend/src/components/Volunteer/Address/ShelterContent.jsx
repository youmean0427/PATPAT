import React from 'react';
import styles from './ShelterContent.module.scss';
export default function ShelterContent({ gugunCode }) {
  // 보호소 코드 보내주는 코드 작성하기

  const result = [
    {
      shelterId: '101010',
    },
    {
      shelterId: '594959',
    },
  ];
  const [activeTab, setActiveTab] = React.useState('');

  function onclick(event) {
    setActiveTab(event.target.value);
  }
  return (
    <div className={styles.address}>
      <ul>
        {gugunCode === '1357'
          ? result.map(item => (
              <li
                key={item.shelterId}
                style={{
                  backgroundColor: activeTab === item.shelterId ? '#5d6dbe' : null,
                  color: activeTab === item.shelterId ? '#ffffff' : null,
                }}
              >
                <button onClick={onclick} value={item.shelterId}>
                  {item.shelterId}
                </button>
              </li>
            ))
          : null}
      </ul>
      <div></div>
    </div>
  );
}
