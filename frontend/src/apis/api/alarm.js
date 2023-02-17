import { authInstance } from 'apis/utils';

/**
 * GET : 알람 리스트를 가져온다.
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const getAlarmList = async () => {
  const { data } = await authInstance.get(`alarm`);
  return data;
};

/**
 * GET : 알람 조회
 * @param {int} : 알람 고유 ID
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const alarmDetail = async alarmId => {
  const { data } = await authInstance.get(`alarm/${alarmId}`);
  return data;
};

/**
 * DELETE : 개별 알람 삭제
 * @param {int} : 알람 고유 ID
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const deleteAlarm = async alarmId => {
  const { data } = await authInstance.delete(`alarm/${alarmId}`);
  return data;
};

/**
 * DELETE : 전체 알람 삭제
 * @returns [] : typeCode에 따른 Item을 가진 배열
 */
export const deleteAllAlarm = async () => {
  const { data } = await authInstance.delete(`alarm/all`);
  return data;
};
