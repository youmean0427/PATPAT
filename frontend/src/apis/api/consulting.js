import { authInstance } from 'apis/utils';

// GET

/**
 * GET : 나의 상담 내역 조회 리스트
 * @param {int} limit
 * @param {int} offset
 * @param {int} stateCode
 * @returns
 * [
    {
      "address": "주소",
      "consultingId": 0,
      "endTime": "2023-01-30T01:52:05.186Z",
      "shelterId": 0,
      "shelterName": "보호소 이름",
      "startTime": "2023-01-30T01:52:05.186Z",
      "state": "대기,승인,거절,완료",
      "userName": "유저 이름"
    }
  ]
 */
export const getMyConsultations = async (limit, offset, stateCode) => {
  const { data } = await authInstance.get(`/consultations?limit=${limit}&offSet=${offset}&stateCode=${stateCode}`);
  return data;
};

/**
 * 보호소 관리 페이지에서의 상담 신청 내역 리스트
 * @param {int} shelterId
 * @param {*} limit
 * @param {*} offset
 * @returns
 */
export const getShelterConsultations = async (shelterId, limit, offset, stateCode) => {
  const { data } = await authInstance.get(
    `/consultations/shelters?limit=${limit}&offSet=${offset}&shelterId=${shelterId}&stateCode=${stateCode}`
  );
  return data;
};

/**
 * 보호소 내 상담 예약 가능한 시간 리스트
 * @param {int} shelterId
 * @param {string} date
 * @returns
 */
export const getShelterConsultationsTime = async (shelterId, date) => {
  const { data } = await authInstance.get(`/consultations/shelters/${shelterId}?date=${date}`);
  return data;
};

// POST

/**
 * POST : 상담 예약 신청
 * @param {json} data 상담 시작 날짜 , 종료 날짜 , shelterId
 * @returns
 */
export const createConsultant = async data => {
  const res = await authInstance.post('/consultations', data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};

// PUT

/**
 * PUT 상담 수정 : 시간 , 상태
 * @param {int} consultingId
 * @param {json} data
 */
export const updateConsultant = async (consultingId, data) => {
  const res = await authInstance.put(`/consultations/${consultingId}`, data, {
    headers: { 'Content-Type': 'application/json' },
  });
  return res;
};
