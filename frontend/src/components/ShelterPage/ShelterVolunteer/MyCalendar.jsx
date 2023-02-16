import { useQuery } from '@tanstack/react-query';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import moment from 'moment';
import React from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import { useRecoilState, useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import Toolbar from './Toolbar';
import './MyCalendar.scss';
import useModal from 'hooks/useModal';
import EnrollVolunteerModal from 'components/Common/Modal/shelters/EnrollVolunteerModal';
import { useState } from 'react';
import { useEffect } from 'react';
import { volNoticeListPerMonthState } from 'recoil/atoms/shelter';
import ManageVolNoticeModal from 'components/Common/Modal/shelters/ManageVolNoticeModal';

export default function MyCalendar() {
  const myShelterId = useRecoilValue(myShelterIdState);
  const [date, setDate] = useState({ year: 2023, month: 2 });
  const [data, setData] = useRecoilState(volNoticeListPerMonthState);
  const [noticeId, setNoticeId] = useState();
  const [click, setClick] = useState(false);
  const [selectDate, setSelectDate] = useState();
  const [isModalOpen, setIsModalOpen] = useState(false);
  useEffect(() => {
    getVolNoticePerMonth(myShelterId, date.year, date.month).then(res => setData(res));
  }, [click]);
  const handleClickModalOpen1 = () => {
    setIsModalOpen(true);
  };

  const handleClickModalClose1 = () => {
    setIsModalOpen(false);
  };
  moment.locale('ko-KR');
  const localizer = momentLocalizer(moment);
  const handleClickSlot = ({ start }) => {
    console.log(start);
    setSelectDate(start);
    handleClickModalOpen();
  };
  const handleClickSelect = select => {
    setNoticeId(select.noticeId);
    handleClickModalOpen1();
  };
  const handleClickNavigate = date => {
    setDate({ year: date.getFullYear(), month: date.getMonth() + 1 });
    setClick(prev => !prev);
  };
  const eventHandlePropGetter = event => {
    const backgroundColor = event.magam ? '#e74c3c' : '#a1887f';
    return { style: { backgroundColor } };
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
        defaultView="month"
        onSelectSlot={handleClickSlot}
        onSelectEvent={handleClickSelect}
        onNavigate={handleClickNavigate}
        eventPropGetter={eventHandlePropGetter}
      />
      {isOpen && (
        <EnrollVolunteerModal date={selectDate} isOpen={isOpen} handleClickModalClose={handleClickModalClose} />
      )}
      {isModalOpen && (
        <ManageVolNoticeModal
          setClick={setClick}
          noticeId={noticeId}
          isOpen={isModalOpen}
          handleClickModalClose={handleClickModalClose1}
        />
      )}
    </>
  );
}

const convertCalendarData = list => {
  if (list) {
    return list.map(item => {
      const vDate = item.volunteerDate;
      const date = new Date(vDate);
      return {
        noticeId: item.noticeId,
        title: item.stateCode === 1 ? `(마감)${item.title}` : item.title,
        start: date,
        end: date,
        magam: item.stateCode === 1 ? true : false,
      };
    });
  } else {
    return [];
  }
};
