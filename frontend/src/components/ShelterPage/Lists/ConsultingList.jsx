import { useQuery } from '@tanstack/react-query';
import { getShelterConsultations } from 'apis/api/consulting';
import React from 'react';
import styles from './ConsultingList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';
import { isTodayConsulting } from 'utils/isTodayConsulting';

export default function ConsultingList({ stateCode, shelterId }) {
  const { data, isLoading } = useQuery({
    queryKey: ['getConsultations', stateCode],
    queryFn: () => {
      return getShelterConsultations(shelterId, 9, 0, stateCode);
    },
  });
  // click : 0 전체 , 1 대기 , 2 승인 , 3 오늘날짜 전체

  if (isLoading) return;
  return data.list.map(item => <ConsultingItem key={item.consultingId} item={item} filterCode={stateCode} />);
}
