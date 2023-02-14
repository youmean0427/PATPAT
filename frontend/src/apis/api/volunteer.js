// GET

import { authInstance, defaultInstance } from 'apis/utils';

/**
 * GET : 봉사활동 신청 공고 찾기 리스트
 * @param {string} keyword
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getVolNoticeList = async (keyword, limit, offset, latitude, longitude) => {
  const { data } = await defaultInstance.get(
    `/volunteers/notices?keyword=${keyword}&limit=${limit}&offSet=${offset}&latitude=${latitude}&longitude=${longitude}`
  );
  return data;
};

/**
 * GET : 봉사활동 신청 공고 상세 정보 데이터
 * @param {int} noticeId
 * @returns
 */
export const getVolNoticeDetail = async noticeId => {
  const { data } = await defaultInstance.get(`/volunteers/notices/detail/${noticeId}`);
  return data;
};

/**
 * GET : 일반 User가 보호소에 들어간 경우 카드 형식으로 보여줘야 하는 경우 (Paination 필요)
 * @param {int} shelterId
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getVolNoticeListOfShelter = async (shelterId, limit, offset) => {
  const { data } = await authInstance.get(
    `/volunteers/notices/shelters?shelterId=${shelterId}&limit=${limit}&offSet=${offset}`
  );
  return data;
};

/**
 * GET : 보호소의 한달 공고 리스트
 * @returns
 */
export const getVolNoticePerMonth = async (shelterId, year, month) => {
  const { data } = await defaultInstance.get(`/volunteers/months?month=${month}&year=${year}&shelterId=${shelterId}`);
  return data;
};

/**
 * GET : User의 봉사 예약 신청 내역 리스트
 * @param {int} userId
 * @returns
 */
export const getVolReservationOfUser = async (limit, offset) => {
  const { data } = await authInstance.get(`/volunteers/reservations?limit=${limit}&offSet=${offset}`);
  return data;
};

/**
 * GET : Shelter의 봉사 공고의 신청 내역 리스트
 * @param {int} shelterId
 * @returns
 */
export const getVolReservationOfShelter = async shelterId => {
  const { data } = await authInstance.get(`/volunteers/reservations?shelterId=${shelterId}`);
  return data;
};

/**
 * GET : 봉사 신청내역 상세 조회
 * @param {int} scheduleId
 * @returns
 */
export const getVolReservationOfUserDetail = async scheduleId => {
  const { data } = await authInstance.get(`/volunteers/schedules/${scheduleId}`);
  return data;
};

/**
 * GET : user가 지원한 봉사 지원서 조회 확인
 * @param {int} noticeId
 * @returns
 */
export const getVolReservationOfUserCheck = async noticeId => {
  const { data } = await authInstance.get(`/volunteers/reservations/check/${noticeId}`);
  return data;
};

// POST

/**
 * POST : 보호소에서 봉사활동 신청 공고 등록
 * @param {json} data
 * @returns
 */
export const createVolNotice = async data => {
  const res = await authInstance.post(`/volunteers/notices`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

/**
 * 개인이 봉사 활동 신청
 * @param {int} volunteerId 봉사활동 공고 id
 * @param {int} capacity 인원 수
 * @returns
 */
export const applyVolReservation = async data => {
  const res = await authInstance.post(`/volunteers/reservations`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

// PUT
/**
 * PUT : 보호소 봉사활동 공고 정보 수정
 * @param {int} volunteerId
 * @param {json} data
 * @returns
 */
export const updateVolNotice = async (volunteerId, data) => {
  const res = await authInstance.put(`/volunteers/notices/${volunteerId}`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

/**
 * PUT : 봉사 지원서 정보 수정
 * @param {int} volunteerId 봉사 공고 id
 * @param {int} capacity 인원수
 * @param {int} state
 * @returns
 */
export const updateVolReservation = async (volunteerId, capacity, state) => {
  const res = await authInstance.put(
    `/volunteers/reservations?volunteerId=${volunteerId}&capacity=${capacity}&state=${state}`
  );
  return res;
};
