import styles from './VolunteerDetail.module.scss';
import React, { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getVolReservationOfUserDetail } from 'apis/api/volunteer';
import ScheduleItem from 'components/Volunteer/Schedule/ScheduleItem';

export default function VolunteerDetail({ items }) {
  // console.log('Modalitem', items);

  // useState

  const [scheduleId, setScheduleId] = useState(items.scheduleId);
  // const [scheduleId, setScheduleId] = useState(1);
  const [open, setOpen] = useState(0);

  // useQuery
  // console.log(items.scheduleId);
  // const { data, isLoading } = useQuery({
  //   queryKey: ['getVolNoticePerMonth'],
  //   queryFn: () => getVolReservationOfUserDetail(scheduleId),
  // });

  // if (isLoading) return;

  return (
    <div className={styles.container}>
      <div>{items.title}</div>
      <div className={styles['container-scheduleBnt']}>
        {scheduleId.map((item, index) => (
          <div key={index} className={styles.scheduleBnt}>
            <button onClick={() => setOpen({ index })}>{index + 1}</button>
          </div>
        ))}
      </div>
      <hr />
      <div className={styles.scheduleItem}>
        {scheduleId.map((item, index) => (
          <ScheduleItem item={item} key={index} index={index} open={open} />
        ))}
      </div>
    </div>
  );
}
