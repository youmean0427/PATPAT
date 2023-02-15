import { useQuery } from '@tanstack/react-query';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import MyCalendar from 'components/ShelterPage/ShelterVolunteer/MyCalendar';
import ShelterVolNoticeList from 'components/ShelterPage/ShelterVolunteer/ShelterVolNoticeList';
import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import { useLocation } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { myShelterIdState } from 'recoil/atoms/user';
import { checkMyShelter } from 'utils/checkMyShelter';
import styles from './ShelterVolunteer.module.scss';
export default function ShelterVolunteer() {
  const {
    state: { shelterId },
  } = useLocation();
  const myShelterId = useRecoilValue(myShelterIdState);
  return (
    <ShelterContainer title={checkMyShelter(shelterId, myShelterId) ? '봉사 관리' : '봉사 신청'}>
      {checkMyShelter(shelterId, myShelterId) ? <MyCalendar /> : <ShelterVolNoticeList />}
    </ShelterContainer>
  );
}
