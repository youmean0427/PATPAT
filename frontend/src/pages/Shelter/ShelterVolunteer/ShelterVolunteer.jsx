import { useQuery } from '@tanstack/react-query';
import { getVolNoticePerMonth } from 'apis/api/volunteer';
import MyCalendar from 'components/ShelterPage/ShelterVolunteer/MyCalendar';
import ShelterContainer from 'containers/ShelterContainer';
import React from 'react';
import styles from './ShelterVolunteer.module.scss';
export default function ShelterVolunteer() {
  return (
    <ShelterContainer title="봉사 관리">
      <MyCalendar />
    </ShelterContainer>
  );
}
