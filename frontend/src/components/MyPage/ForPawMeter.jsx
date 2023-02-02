import React, { useState } from 'react';
import styles from './ForPawMeter.module.scss';
import ForPawDog from 'assets/images/ForPawDog.gif';
import ModifyBtn from 'assets/images/modify-profile.png';
import ForPawInfo from 'assets/images/forpaw-info.png';

export default function ForPawMeter() {
  const [click, setClick] = useState(false);

  const profile_url =
    'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxEHBhAIBw8OFRUODw8QEBMSEBAPEA4SFhMWFhYSFx8YHTQgGRoxHRMTITEhJSkuLi4uFx8zODMsNyktLisBCgoKDg0OGhAQGjYlHyU1Ky0tKy0wKy0tLi0tLS0rLS0tLS0tLS0tKy03NystLSsrNS0tLS03Kzc3LS0rLS0tN//AABEIAOEA4QMBIgACEQEDEQH/xAAaAAEAAgMBAAAAAAAAAAAAAAAABAUCAwYB/8QAMxABAAECAwUFBwQDAQAAAAAAAAECAwQRcQUhMTJBEhNRYXI0gZGhsdHhIkKSwSMzghT/xAAZAQEBAQEBAQAAAAAAAAAAAAAAAwIBBAX/xAAcEQEBAQEBAQEBAQAAAAAAAAAAAQIRAzESQSH/2gAMAwEAAhEDEQA/AOsAe980AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHvHgscLs/d27/8fu5bx2S1At2qrk5W4mdISKdn3J6RGs/ZbUxFMZUxlo9Y/ak84qJ2fcjhETpLRcs1Wv8AZTMfRfExnGUn7L5xzotMVs+Ko7VjdPh0nTwVkx2Zyno3L1O5seAOuAAAAAAAAAAAAAAAAAAANlm33t2KI6yCds3DZR39f/Pl5rAiMoyjoJW9Xk5ABx0AAQto4bt0d7Rxjj5wmhLws7HOjdirXc36qI8d2ktKzzgAAAAAAAAAAAAAAAAACZsunPE5+FMz/X9oabsqcsRMeNM/WHNfHc/VqAkuAAAAAAq9rU5XqavGnL4T+UFYbWn/ACUx5T9fwr1c/ENfQB1wAAAAAAAAAAAAAAAAbsJc7rEU1T45TpO5pAjohFwGI7612auNO6fOPFKRv+PRL0AAAABoxl/uLOccZ3U/cLeK3H3O8xU5dP0/D85oz14tHnt6AAAAAAAAAAAAAAAAAAAAztXJtVxXRxhcYXFU4iMuE9Y+ykexOW+HLOu51x0IqLW0K7cZVZVa8fik07Tp/dTV7piWPzVZuJwgztOn9tNXvyhHu7Qrr3UZU6b5c/NLuLDEYmmxT+rj0jrKnvXZvXO3X+IjwYTPanOp4pM8T1roA6yAAAAAAAAAAAAAAAAAAAAAADKmmauWJnSM2cWK54UV/wAZBqG2cPXHGiv+MsJomnmiY1jIOMQAAAAAAAAAAAAAAAAAAABnatTdq7NuM/6WVjZ0U772+fDp+XLZHZm1W0W5uTlREzpCVb2dVVvrmI+crSmmKYypiI03PWLtSec/qJb2dRTzZz78o+TfRYoo5aafhvbBztakkAHHQAGFVmmvmppn3Q0V7Poq4RMaT90oOlkqsubNmN9uqJ13SiXbNVqcrkTH0XxMZxlLU1WLiOdFtf2fTXvt/pn5K69Yqs1ZXI0npLcsqdzY1AOuAAAAAAAAAADdhcPOIudmOEcZ8GleYSz3NiKevGdXNXjWc9rOzaizR2bcfedWYJLAAAAAAAAAAAADyuiK6ezXGcS9AU2Mwv8A56s6eWeHl5Iy/vW4u2poq6/LzUMx2ZynpuUzeo7zyvAGmQAAAAAAAHtPNGroXPU80aw6FjanmAMKAAAAAAAAAAAAAACixW7E1+qfqvVHi/aq/VLeGPT40gNpAAAAAAAAMqeaNYdA5+nmjWHQMbU8wBhQAAAAAAAAAAAAAAUeL9qr9UrxR4v2qv1S3hj0+NIDaQAAAAAAADKnmjWHQOfo541h0DG1PMAYUAAAAAAAAAAAAAAFHi/aq/VK8UeL9qr9Ut4Y9PjSA2kAAAAAAAAyo541h0AMbU8wBhQAAAAAAAAAAAAAAUeL9qr9Ug3hj0+NIDaQAAAD/9k=';
  const distance = 0;

  return (
    <div className={styles.ForPaw}>
      <div className={styles.container}>
        {click ? (
          <div className={styles['content-text']}>
            For Paw Meter는 봉사시간, 후기, 정보공유 등을 종합해서 만든 관심 지표입니다.
          </div>
        ) : null}
        <div className={styles.content}>
          <p>ForPawMeter</p>
          <img
            title="포포미터 정보"
            src={ForPawInfo}
            alt=""
            className={styles['forpaw-info']}
            onClick={() => setClick(cur => !cur)}
          />
        </div>
        <br />
        <img src={ForPawDog} alt="" className={styles['dog-gif']} style={{ marginLeft: `${distance}` }} />
        <img src={profile_url} alt="" className={styles['user-profile']} />
        <img title="프로필 수정" src={ModifyBtn} alt="" className={styles['modify-profile']} />
        <br />
        <div className={styles.road}></div>
      </div>
    </div>
  );
}
