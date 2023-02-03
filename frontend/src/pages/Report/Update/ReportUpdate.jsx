import ReportUpdateContent from 'components/Report/Update/ReportUpdateContent';
import React from 'react';
import { useLocation } from 'react-router-dom';

export default function ReportUpdate() {
  const location = useLocation();
  const items = location.state.data;

  return (
    <div>
      UPDATE
      <ReportUpdateContent items={items} />
    </div>
  );
}
