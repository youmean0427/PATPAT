import ReportMissingDogUpdateContent from 'components/Report/Update/MissingDog/ReportMissingDogUpdateContent';

import React from 'react';
import { useLocation } from 'react-router-dom';

export default function ReportMissingDogUpdate() {
  const location = useLocation();
  const items = location.state.data;
  return (
    <div>
      <ReportMissingDogUpdateContent items={items} />
    </div>
  );
}
