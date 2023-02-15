import { useQuery } from '@tanstack/react-query';
import { getAlarmList, alarmDetail, deleteAlarm, deleteAllAlarm } from 'apis/api/alarm';
import CircleNotificationsIcon from '@mui/icons-material/CircleNotifications';
import Badge from '@mui/material/Badge';
import { useState, useEffect } from 'react';
import styles from './Alarm.module.scss';
import CloseIcon from '@mui/icons-material/Close';
import { useNavigate } from 'react-router';
import { toast } from 'react-toastify';

export default function Alarm() {
  let subscribeUrl = process.env.REACT_APP_API_URL + '/alarm/sub';
  const [message, setMessage] = useState();
  const [isCheck, setIscheck] = useState(false);
  const [show, setShow] = useState(false);
  const [start, setStart] = useState(true);
  const [click, setClick] = useState(false);
  const navigate = useNavigate();

  const handleAlarmDetail = async item => {
    switch (item.msgCode) {
      case 0: {
        // 실종견 등록
        navigate(`report/missing/${item.missingId}`);
        break;
      }
      case 1: {
        // 신규 상담 신청
        navigate(`shelter/${item.shelterId}/consulting`, { state: { shelterId: item.shelterId } });
        break;
      }
      case 2: {
        // 신규 봉사 신청
        navigate(`shelter/${item.shelterId}/volunteer`, { state: { shelterId: item.shelterId } });
        break;
      }
      case 3: {
        // 유사견
        navigate('/mypage/missing');
        break;
      }
      case 4: {
        // 상담 승인
        navigate('/mypage/consulting');
        break;
      }
      case 5: {
        // 상담 거부
        navigate('/mypage/consulting');
        break;
      }
      case 6: {
        // 봉사 승인
        navigate('/mypage/volunteer');
        break;
      }
      case 7: {
        // 봉사 거부
        navigate('/mypage/volunteer');
        break;
      }
      case 8: {
        // 상담방 생성
        navigate('/mypage/consulting');
        break;
      }
      default: {
        break;
      }
    }
  };
  useEffect(() => {
    if (localStorage.getItem('user') != null) {
      let userId = JSON.parse(localStorage.getItem('user')).userId;
      let eventSource = new EventSource(subscribeUrl + '?userId=' + userId);

      eventSource.addEventListener('connect', function (event) {
        setMessage(event.data);
      });

      // 보호소
      // 신규 실종견 등록
      eventSource.addEventListener('addMissing', function (event) {
        setMessage(event.data);
      });
      // 신규 상담 신청
      eventSource.addEventListener('addConsulting', function (event) {
        setMessage(event.data);
      });
      // 신규 봉사 신청
      eventSource.addEventListener('addVolunteer', function (event) {
        setMessage(event.data);
      });

      // 개인사용자
      // 유사견 등록
      eventSource.addEventListener('addProtect', function (event) {
        setMessage(event.data);
      });
      // 상담 승인
      eventSource.addEventListener('accessConsulting', function (event) {
        setMessage(event.data);
      });
      // 상담 거부
      eventSource.addEventListener('denyConsulting', function (event) {
        setMessage(event.data);
      });
      // 봉사 신청 승인
      eventSource.addEventListener('accessVolunteer', function (event) {
        setMessage(event.data);
      });
      // 봉사 신청 거부
      eventSource.addEventListener('denyVolunteer', function (event) {
        setMessage(event.data);
      });
      // 상담방 생성
      eventSource.addEventListener('createRoom', function (event) {
        setMessage(event.data);
      });

      eventSource.addEventListener('error', function (event) {
        eventSource.close();
      });
    }
  }, []);

  const { data, isLoading } = useQuery({
    queryKey: ['getAlarmList', message, click],
    queryFn: () => {
      return getAlarmList();
    },
  });

  if (isLoading) return;

  return (
    <div className={styles.notify} onClick={() => setShow(cur => !cur)}>
      <Badge badgeContent={data.cntNoRead} color="warning">
        <CircleNotificationsIcon
          onClick={() => {
            setIscheck(false);
            setStart(false);
          }}
          sx={{ fontSize: '25px', color: '#ffd80b' }}
        />
      </Badge>

      {show ? (
        <div className={styles.messages}>
          <div
            className={styles['delete-all']}
            onClick={async () => {
              await deleteAllAlarm();
              setClick(cur => !cur);
              toast('삭제되었습니다.', { type: 'success' });
            }}
          >
            전체 삭제
          </div>
          {data.list.map(item => (
            <div className={styles['alarm-content']} key={item.alarmId}>
              <p className={`${styles['alarm-message']} ${!item.checkRead ? null : styles['alarm-message-read']}`}>
                <span
                  title="확인하러 가기"
                  onClick={async () => {
                    await alarmDetail(item.alarmId);
                    handleAlarmDetail(item);
                    setClick(cur => !cur);
                  }}
                >
                  {item.msg}
                </span>
              </p>
              <p
                className={styles.close}
                onClick={async () => {
                  await deleteAlarm(item.alarmId);
                  setClick(cur => !cur);
                  toast('삭제되었습니다.', { type: 'success' });
                }}
              >
                <CloseIcon />
              </p>
            </div>
          ))}
        </div>
      ) : start ? (
        <div style={{ display: 'none' }}></div>
      ) : (
        <div className={styles['messages-hide']}></div>
      )}
    </div>
  );
}
