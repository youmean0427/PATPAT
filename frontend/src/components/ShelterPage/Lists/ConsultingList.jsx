import { useQuery } from '@tanstack/react-query';
import { getShelterConsultations } from 'apis/api/consulting';
import React from 'react';
import styles from './ConsultingList.module.scss';
import ConsultingItem from '../Items/ConsultingItem';

export default function ConsultingList({ shelterId }) {
  const { data, isLoading } = useQuery({
    queryKey: ['getConsultations'],
    queryFn: () => getShelterConsultations(shelterId, 4, 0),
  });

  if (isLoading) return;
  return data.list.map(item => <ConsultingItem key={item.consultingId} item={item} />);
}
