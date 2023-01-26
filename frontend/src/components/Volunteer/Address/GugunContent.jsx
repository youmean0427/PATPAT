import React from 'react';
import styles from './GugunContent.module.scss';
export default function GugunContent({ sidoCode, getGugunFunc }) {
  // sidoCode에 맞게 gugun 요청

  const result = [
    {
      gugunCode: '1357',
      gugunName: '강서구',
    },
    {
      gugunCode: '2468',
      gugunName: '북구',
    },
    {
      gugunCode: '9876',
      gugunName: '남구',
    },
    {
      gugunCode: '1864',
      gugunName: '동구',
    },
  ];

  const [activeTab, setActiveTab] = React.useState('');
  function onclick(event) {
    setActiveTab(event.target.value);
    getGugunFunc(event.target.value);
  }
  // console.log(activeTab);

  return (
    <div className={styles.address}>
      <ul>
        {sidoCode === '1234'
          ? result.map((item, index) => (
              <li
                key={index}
                style={{
                  backgroundColor: activeTab === item.gugunCode ? '#5d6dbe' : null,
                  color: activeTab === item.gugunCode ? '#ffffff' : null,
                }}
              >
                <button onClick={onclick} value={item.gugunCode}>
                  {item.gugunName}
                </button>
              </li>
            ))
          : null}
      </ul>
    </div>
  );
}
