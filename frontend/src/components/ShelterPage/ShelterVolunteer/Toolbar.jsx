import React from 'react';

function Toolbar({ onNavigate, date }) {
  const navigate = action => {
    onNavigate(action);
  };

  return (
    <div className="rbc-toolbar">
      <span className="rbc-btn-group">
        <button type="button" onClick={navigate.bind(null, 'PREV')}>
          이전
        </button>
        <span className="rbc-toolbar-label">{`${date.getFullYear()}년 ${date.getMonth() + 1}월`}</span>
        <button type="button" onClick={navigate.bind(null, 'NEXT')}>
          다음
        </button>
      </span>
    </div>
  );
}

export default React.memo(Toolbar);
