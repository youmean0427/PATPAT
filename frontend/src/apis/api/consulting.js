import { authInstance } from 'apis/utils';

// GET

/**
 * GET : 나의 상담 내역 조회 리스트
 * @param {int} limit
 * @param {int} offset
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
export const getMyConsultations = async (limit, offset, userId) => {
  const { data } = await authInstance.get(`/consultations?userId=${userId}&limit=${limit}&offSet=${offset}`);
  return data;
};

/**
 * 보호소 관리 페이지에서의 상담 신청 내역 리스트
 * @param {int} shelterId
 * @param {*} limit
 * @param {*} offset
 * @returns
 */
export const getShelterConsultations = async (shelterId, limit, offset) => {
  const { data } = await authInstance.get(
    `/consultations/shelters?limit=${limit}&offSet=${offset}&shelterId=${shelterId}`
  );
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
  const res = await authInstance.put(`/consultations/${consultingId}`, {
    headers: { 'Content-Type': 'application/json' },
    body: { ...data },
  });
  return res;
};
