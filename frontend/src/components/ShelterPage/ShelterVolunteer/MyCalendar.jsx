import { useQuery } from '@tanstack/react-query';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import moment from 'moment';
import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import Toolbar from './Toolbar';
import './MyCalendar.scss';
import useModal from 'hooks/useModal';
import EnrollVolunteerModal from 'components/Common/Modal/shelters/EnrollVolunteerModal';
import { useState } from 'react';
import { useEffect } from 'react';

export default function MyCalendar() {
  const myShelterId = useRecoilValue(myShelterIdState);
  const [date, setDate] = useState({ year: 2023, month: 2 });
  const [data, setData] = useState([]);
  const [click, setClick] = useState(false);
  useEffect(() => {
    getVolNoticePerMonth(myShelterId, date.year, date.month).then(res => setData(res));
  }, [click]);
  moment.locale('ko-KR');
  const localizer = momentLocalizer(moment);
  const handleClickSelect = ({ start }) => {
    const year = start.getFullYear();
    const month = start.getMonth() + 1;
    const date = start.getDate();
    handleClickModalOpen();
  };
  const handleClickNavigate = date => {
    setDate({ year: date.getFullYear(), month: date.getMonth() + 1 });
    setClick(prev => !prev);
  };

  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();

  return (
    <>
      <Calendar
        events={convertCalendarData(data)}
        localizer={localizer}
        style={{ height: 500 }}
        components={{ toolbar: Toolbar }}
        selectable
        onSelectSlot={handleClickSelect}
        onSelectEvent={e => console.log(e)}
        onNavigate={handleClickNavigate}
      />
      {isOpen && <EnrollVolunteerModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} />}
    </>
  );
}

const convertCalendarData = list => {
  if (list) {
    return list.map(item => {
      const vDate = item.volunteerDate;
      const date = new Date(vDate);
      return { title: item.title, start: date, end: date };
    });
  } else {
    return [];
  }
};
