import React from 'react';
import { useLocation } from 'react-router-dom';
import ReportCreateContent from '../../../components/Report/Create/ReportCreateContent';

export default function ReportUpdate() {
  const location = useLocation();
  const items = location.state.data;
  return <div>{items}</div>;
}
