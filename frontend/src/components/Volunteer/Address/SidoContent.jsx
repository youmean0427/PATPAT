import React from 'react';
import GugunContent from './GugunContent';
export default function SidoContent() {
  return (
    <div>
      <ul>
        <li>
          <input type="button" value="서울" />
          <GugunContent title="서울" />
        </li>
        <li>
          <input type="button" value="서울" />
        </li>
        <li>
          <input type="button" value="서울" />
        </li>
        <li>
          <input type="button" value="서울" />
        </li>
      </ul>
    </div>
  );
}
