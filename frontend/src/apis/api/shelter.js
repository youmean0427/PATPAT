import { authInstance, defaultInstance } from 'apis/utils';

// GET

/**
 * GET : 보호소 찾기
 * @param {*} breedName 견종 이름
 * @param {*} gugunCode 구군 코드
 * @param {*} sidoCode 시도 코드
 * @returns 보호소 리스트
 */
export const getShelterList = async (sidoCode, gugunCode, breedId, limit, offSet) => {
  const { data } = await defaultInstance.get('/shelters', { params: { breedId, gugunCode, sidoCode, limit, offSet } });
  return data;
};

/**
 * GET : 보호소 상세정보
 * @param {*} shelterId
 * @returns 보호소 상세 정보 데이터
 */
export const getShelterDetail = async shelterId => {
  const { data } = await defaultInstance.get(`/shelters/${shelterId}`);
  return data;
};

/**
 * GET : 시도 리스트
 * @returns [] : 시도 데이터 리스트
 */
export const getSidoList = async () => {
  const { data } = await defaultInstance.get('/shelters/sidos');
  return data;
};

/**
 * GET : 구군 리스트
 * @param {string} sidoCode sidoCode에 따른
 * @returns [] : 구군 데이터 리스트
 */
export const getGugunList = async sidoCode => {
  const { data } = await defaultInstance.get(`/shelters/guguns?sidoCode=${sidoCode}`);
  return data;
};

/**
 * GET : 견종 리스트
 * @returns [] : 견종 데이터 리스트
 */
export const getBreedsList = async () => {
  const { data } = await defaultInstance.get(`/shelters/breeds`);
  return data;
};

/**
 * GET : MBTI 결과에 따른 견종 결과 데이터
 * @param {string} mbtiId ex) infp,isfp ...
 * @returns 견종 결과 테이터 -> breedName,files,desc,title,mbti
 */
export const getMbtiBreedInfo = async mbtiId => {
  const { data } = await defaultInstance.get(`/shelters/mbti/${mbtiId}`);
  return data;
};

/**
 * GET : MBTI 결과로 얻은 견종을 가진 보호소의 개수를 리턴 (전국 개수,시도별 개수)
 * @param {int} breedId
 * @returns
 */
export const getCountShelterByBreed = async breedId => {
  const { data } = await defaultInstance.get(`/shelters/mbti/count/${breedId}`);
  return data;
};

// POST

/**
 * POST : 보호소 인증 후 등록
 * @param {string} code
 * @param {string} name
 * @returns
 */
export const createShelter = async data => {
  const res = await authInstance.post(`/shelters`, data);
  return res;
};

export const getAuthShelterList = async () => {
  const { data } = await authInstance.get('/shelters/list');
  return data;
};

/**
 * POST : 보호소 정보 업데이트
 * @param {FormData} formdata
 * @param {int} shelterId
 * @returns
 */
export const updateShelter = async formdata => {
  const res = await authInstance.post(`/shelters/update`, formdata, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return res;
};
