import styles from './VolunteerDetail.module.scss';
import React, { useState } from 'react';

import ScheduleItem from 'components/Volunteer/Schedule/ScheduleItem';

export default function VolunteerDetail({ items }) {
  // useState

  const [scheduleId] = useState(items.scheduleId);
  // const [scheduleId, setScheduleId] = useState(1);
  const [open, setOpen] = useState(0);
  const [active, setActive] = useState(false);

  return (
    <div className={styles.container}>
      <div className={styles.title}>
        공고제목<span>{items.title}</span>
      </div>
      <div className={styles.volunteerDate}>
        봉사날짜<span>{items.volunteerDate}</span>
      </div>
      {/* Button */}
      <div className={styles['container-scheduleBnt']}>
        {scheduleId.map((item, index) => (
          <div key={index}>
            {index === open.index && active === true ? (
              <button
                className={styles.scheduleBntActive}
                onClick={() => {
                  setOpen({ index });
                  setActive(true);
                }}
              >
                {index + 1}차
              </button>
            ) : (
              <button
                className={styles.scheduleBnt}
                onClick={() => {
                  setOpen({ index });
                  setActive(true);
                }}
              >
                {index + 1}차
              </button>
            )}
          </div>
        ))}
      </div>
      <hr />
      <div className={styles.scheduleItem}>
        {scheduleId.map((item, index) => (
          <ScheduleItem
            item={item}
            volunteerData={items.volunteerData}
            noticeId={items.noticeId}
            key={index}
            index={index}
            open={open}
          />
        ))}
      </div>
    </div>
  );
}
