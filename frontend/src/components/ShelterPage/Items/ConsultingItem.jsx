import React from 'react';
import styles from './ConsultingItem.module.scss';
import ConsultingCard from 'components/Common/Card/ConsultingCard';
import { Box, LinearProgress, Typography } from '@mui/material';
import { formatDate, formatTime } from 'utils/formatDate';
import ConsultingBadge from 'components/Common/Badge/ConsultingBadge';
import useModal from 'hooks/useModal';
import ConsultingStateModal from 'components/Common/Modal/shelters/ConsultingStateModal';

export default function ConsultingItem({ item, filterCode }) {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();

  return (
    <ConsultingCard>
      <div className={styles.info}>
        <div className={styles.user}>
          <img src={item.userProfileUrl} alt="프로필 이미지" />
          <div className={styles['user-name']}>{item.userName}님</div>
        </div>
        <div className={styles.desc}>
          <div className={styles['desc-item']}>
            <span>{item.shelterDogName}</span>
          </div>
          <div className={styles['desc-item']}>
            <span className={styles['desc-item-title']}>{formatDate(item.consultingDate)}</span>
          </div>
          <div className={styles['desc-item']}>
            <span className={styles['desc-item-title']}>{formatTime(item.timeCode)} 예정</span>
          </div>
        </div>
      </div>
      <div className={styles.exp}>
        <span>ForPawMeter</span>
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <Box sx={{ width: '100%', mr: 1 }}>
            <LinearProgress variant="determinate" value={item.userExp} color="inherit" />
          </Box>
          <Box sx={{ mr: 1 }}>
            <Typography color="text.secondary">{item.userExp}</Typography>
          </Box>
        </Box>
      </div>
      <ConsultingBadge handleClickModalOpen={handleClickModalOpen} state={item.state} stateCode={item.stateCode} />
      {isOpen && (
        <ConsultingStateModal
          isOpen={isOpen}
          handleClickModalClose={handleClickModalClose}
          state={item.state}
          stateCode={item.stateCode}
          consultingId={item.consultingId}
          shelterId={item.shelterId}
          shelterName={item.shelterName}
          filterCode={filterCode}
          userName={item.userName}
          consultingDate={item.consultingDate}
        />
      )}
    </ConsultingCard>
  );
}
