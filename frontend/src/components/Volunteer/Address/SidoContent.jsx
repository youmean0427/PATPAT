import React from 'react';
import styles from './SidoContent.module.scss';
export default function SidoContent({ getSidoFunc }) {
  const result = [
    {
      sidoCode: '1234',
      sidoName: '서울',
    },
    {
      sidoCode: '5678',
      sidoName: '대전',
    },
    {
      sidoCode: '91011',
      sidoName: '대구',
    },
    {
      sidoCode: '121314',
      sidoName: '부산',
    },
  ];

  const [activeTab, setActiveTab] = React.useState('');
  function onclick(event) {
    getSidoFunc(event.target.value);
    setActiveTab(event.target.value);
  }
  return (
    <div className={styles.address}>
      <ul>
        {result.map((item, index) => (
          <li
            key={index}
            style={{
              backgroundColor: activeTab === item.sidoCode ? '#5d6dbe' : null,
              color: activeTab === item.sidoCode ? '#ffffff' : null,
            }}
          >
            <button onClick={onclick} value={item.sidoCode}>
              {item.sidoName}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
