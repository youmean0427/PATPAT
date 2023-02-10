import { authInstance, defaultInstance } from 'apis/utils';

// GET

/**
 * GET : 보호소 보호동물 리스트
 * @param {int} code 0 : 안락사 , 1 : 최신순
 * @param {int} limit 리스트로 가져올 item 개수
 * @param {int} offset skip 할 개수
 * @returns [] : code에 따른 Item을 가진 배열
 */
export const getProtectList = async (code, limit, offset) => {
  const { data } = await defaultInstance.get(`/protects?code=${code}&limit=${limit}&offSet=${offset}`);
  return data;
};

/**
 * GET : 보호소 보호동물 상세 정보
 * @param {*} protectId
 * @returns protectId에 해당하는 보호동물의 데이터
 */
export const getProtectDetail = async protectId => {
  const { data } = await defaultInstance.get(`/protects/${protectId}`);
  return data;
};

/**
 * GET : 특정 보호소의 보호 동물 리스트
 * @param {int} shelterId
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getProtectListOfShelter = async (shelterId, code, limit, offset) => {
  const { data } = await defaultInstance.get(
    `/protects/shelters?shelterId=${shelterId}&code=${code}&limit=${limit}&offSet=${offset}`
  );
  return data;
};
// POST

/**
 * POST : 보호소 보호동물 개별 등록
 * @param {FormData} formData
 * @returns 성공 , 실패
 */
export const createProtect = async formData => {
  const res = await authInstance.post(`/protects`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};

/**
 * POST : 보호소 보호동물 상세 정보 수정
 * @param {FormData} formData
 * @param {int} protectId
 * @returns 성공 , 실패
 */
export const updateProtect = async (formData, protectId) => {
  const res = await authInstance.post(`/protects/${protectId}`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};

export const enrollProtectByExcel = async formData => {
  const res = await authInstance.post('/protects/batches', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};
