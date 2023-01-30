// GET

import { authInstance, defaultInstance } from 'apis/utils';

// /report/missing -> 전체 조회
// /report/missing/:userId -> 해당 유저의 실종 리스트
// /report/missing/detail/:missingId -> 실종견 상세 정보 데이터
//

/**
 * GET : 실종견 조회 , 견종과 성별로 필터링 가능
 * @param {int} breedId
 * @param {int} gender 0 : 수컷 , 1 : 암컷
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getMissingDogList = async (breedId, gender, limit, offset) => {
  const { data } = await defaultInstance.get(
    `/reports/missings?breedId=${breedId}&gender=${gender}&limit=${limit}&offset=${offset}`
  );
  return data;
};

/**
 * GET : 현재 유저의 실종견 등록 공고 리스트
 * @param {int} userId
 * @returns
 */
export const getMissingDogListOfUser = async userId => {
  const { data } = await authInstance.get(`/reports/missings/${userId}`);
  return data;
};

/**
 * GET : 실종견 공고의 상세 정보 데이터
 * @param {int} missingId
 * @returns
 */
export const getMissingDogDetail = async missingId => {
  const { data } = await defaultInstance.get(`/reports/missings/detail/${missingId}`);
  return data;
};

/**
 * GET : 임보견 조회, 견종 성별 필터링 가능
 * @param {int} breedId
 * @param {int} gender
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getPersonalDogList = async (breedId, gender, limit, offset) => {
  const { data } = await defaultInstance.get(
    `/reports/personals?breedId=${breedId}&gender=${gender}&limit=${limit}&offset=${offset}`
  );
  return data;
};

/**
 * GET : 임보견 공고 상세 정보 데이터
 * @param {int} personalProtectId
 * @returns
 */
export const getPersonalDogDetail = async personalProtectId => {
  const { data } = await defaultInstance.get(`/reports/personals/${personalProtectId}`);
  return data;
};

/**
 * GET : 내가 등록한 실종견 공고 클릭시 전국 보호소에 존재하는 같은 견종을 리스트로 가져옴
 * @param {int} missingId
 * @param {int} limit
 * @param {int} offset
 * @returns
 */
export const getSimilarDogList = async (missingId, limit, offset) => {
  const { data } = await authInstance.get(`/reports/recommends?missingId=${missingId}&limit=${limit}&offset=${offset}`);
  return data;
};

/**
 * GET : 견종과 일치하는 보호 동물의 전체 개수를 리턴
 * @param {int} breedId
 * @returns
 */
export const getTotalSimiralCount = async breedId => {
  const { data } = await defaultInstance.get(`/reports/recomments/count?breedId=${breedId}`);
  return data;
};

// POST

/**
 * POST : 실종, 임보 신고 공고 생성 및 등록
 * @param {int} reportType
 * @param {FormData} formData
 */
export const createReport = async (reportType, formData) => {
  const res = await authInstance.post(`/reports?reportType=${reportType}`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};

/**
 * POST : 실종 ,임보 신고 공고 정보 수정
 * @param {int} reportType 0 : 실종 , 1 : 임보
 * @param {FormData} formData
 */
export const updateReport = async (reportType, formData) => {
  const res = await authInstance.post(`/reports/updates?reportType=${reportType}`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};
