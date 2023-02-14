import moment from 'moment';
import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import Toolbar from './Toolbar';

export default function MyCalendar({ volNoticeList }) {
  moment.locale('ko-KR');
  const localizer = momentLocalizer(moment);
  return (
    <Calendar
      events={convertCalendarData(volNoticeList)}
      localizer={localizer}
      defaultDate={new Date()}
      defaultView="month"
      style={{ height: 500 }}
      components={{ toolbar: Toolbar }}
    />
  );
}

const convertCalendarData = list => {
  return list.map(item => {
    const vDate = item.volunteerDate;
    const date = new Date(vDate);
    return { title: item.title, start: date, end: date };
  });
};
