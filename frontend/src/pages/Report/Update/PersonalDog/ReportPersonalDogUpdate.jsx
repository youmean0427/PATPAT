import ReportPersonalDogUpdateContent from 'components/Report/Update/PersonalDog/ReportPersonalDogUpdateContent';

import React from 'react';
import { useLocation } from 'react-router-dom';

export default function ReportPersonalDogUpdate() {
  const location = useLocation();
  const items = location.state.data;
  return (
    <div>
      <ReportPersonalDogUpdateContent items={items} />
    </div>
  );
}
