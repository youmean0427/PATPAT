import React, { useState, useRef, useEffect } from 'react';
import styles from './waiting.module.scss';
import MicIcon from '@mui/icons-material/Mic';
import MicOffIcon from '@mui/icons-material/MicOff';
import VideocamIcon from '@mui/icons-material/Videocam';
import VideocamOffIcon from '@mui/icons-material/VideocamOff';

import QuitBtn from 'assets/images/quitButton.png';

// 보호소에서 방 생성 전 개인이 상담 참가 시 대기룸 입장
export default function Waiting() {
  const center = 'oo'; //보호소 ID값 받아오면 해당 값을 통해 보호소 이름 저장
  const [audio, setAudio] = useState(true); // 음소거 해제 / 음소거
  const [video, setVideo] = useState(true); // 비디오 시작 / 비디오 중지

  // '▼' / '▲'
  const [audioListBtn, setAudioListBtn] = useState(true);
  const [videoListBtn, setVideoListBtn] = useState(true);

  // audioIcon
  const [audioIcon, setAudioIcon] = useState(true);
  // videoicon
  const [videoIcon, setVideoIcon] = useState(true);

  // 웹캠 연동
  useEffect(() => {
    getMedia();
  }, [video, audio]); // 비디오/오디오 버튼 클릭마다 재설정

  // 비디오 태그
  const videoRef = useRef(null);

  // 웹캠 정보 가져오기(비동기)
  const getMedia = async () => {
    try {
      // 현재 디바이스 정보
      const curDevice = await navigator.mediaDevices.getUserMedia({
        audio: audio,
        video: video,
      });
      // 비디오 태그에 웹캠 연동
      videoRef.current.srcObject = curDevice;
    } catch (e) {}
  };

  // 스트림 리스트 받아오기
  const [cameraList, setCameraList] = useState([]);
  const [audioList, setAudioList] = useState([]);
  // 초기 렌더링 될때마다 카메라 정보 가져옴
  useEffect(() => {
    getCameras();
    getAudios();
  }, []);

  // 사용가능한 카메라 정보 받아오기
  const getCameras = async () => {
    try {
      const devices = await navigator.mediaDevices.enumerateDevices();
      const cameraLabels = devices.filter(device => device.kind === 'videoinput').map(camera => camera.label);
      setCameraList(cameraLabels);
    } catch (e) {}
  };

  // 사용가능한 오디오 정보 받아오기
  const getAudios = () => {
    try {
      const devices = navigator.mediaDevices.enumerateDevices();
      const audioLabels = devices.filter(device => device.kind === 'audioinput').map(audio => audio.label);
      setAudioList(audioLabels);
    } catch (e) {}
  };

  const handleAudio = () => {
    setAudio(current => !current);
    setAudioIcon(current => !current);
  };
  const handleVideo = () => {
    setVideo(current => !current);
    setVideoIcon(current => !current);
  };
  const handleAudioListBtn = () => {
    setAudioListBtn(current => !current);
  };
  const handleVideoListBtn = () => {
    setVideoListBtn(current => !current);
  };

  return (
    <div className={styles['waiting-main']}>
      <div className={styles.contents}>
        <h1>{center}보호소 개인 룸</h1>
        <div>
          <p>
            <b>기다려 주셔서 감사합니다. 호스트가 참여하면 미팅을 시작하겠습니다.</b>
          </p>
          <p>
            <b>호스트에게 귀하가 대기하고 있음을 알리시겠습니까?</b>
          </p>
        </div>
      </div>
      <button className={styles['alarm-btn']}>호스트에게 통지</button>
      <div className={styles['stream-functions']}>
        <div className={styles['audio-functions']}>
          <div className={styles['audio-op']} onClick={handleAudio}>
            <div className={styles['audio-icon']}>{audioIcon ? <MicIcon /> : <MicOffIcon />}</div>
            <div className={styles['audio-text']}>{audio ? '음소거' : '음소거 해제'}</div>
          </div>
          <div className={styles.dropup}>
            <div className={styles['audio-list']} onMouseOver={handleAudioListBtn} onMouseLeave={handleAudioListBtn}>
              {audioListBtn ? '▼' : '▲'}
              <div className={styles['dropup-content']}>
                <a>마이크 배열(디지털 마이크용 인텔® 스마트 사운드 기술)</a>
                <a>커뮤니케이션 - 마이크 배열(디지털 마이크용 인텔® 스마트 사운드 기술)</a>
                <a>기본값 - 마이크 배열(디지털 마이크용 인텔® 스마트 사운드 기술)</a>
              </div>
            </div>
          </div>
        </div>
        <div className={styles['video-functions']}>
          <div className={styles['video-op']} onClick={handleVideo}>
            <div className={styles['video-icon']}>{videoIcon ? <VideocamIcon /> : <VideocamOffIcon />}</div>
            <div className={styles['video-text']}>{video ? '비디오 중지' : '비디오 시작'}</div>
          </div>
          <div className={styles.dropup}>
            <div className={styles['video-list']} onMouseOver={handleVideoListBtn} onMouseLeave={handleVideoListBtn}>
              {videoListBtn ? '▼' : '▲'}
              <div className={styles['dropup-content2']}>
                <a>720p HD Camera (2b7e:b500)</a>
              </div>
            </div>
          </div>
        </div>
        <div className={styles['quit-functions']}>
          <img src={QuitBtn} alt="나가기" className={styles.quitBtn} />
        </div>
      </div>
      <video ref={videoRef} autoPlay playsInline className={styles.myFace} />
    </div>
  );
}
