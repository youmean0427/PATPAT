import { authInstance, defaultInstance } from 'apis/utils';

// GET

/**
 * GET : 보호소 찾기
 * @param {*} breedName 견종 이름
 * @param {*} gugunCode 구군 코드
 * @param {*} sidoCode 시도 코드
 * @returns 보호소 리스트
 */
export const getShelterList = async (breedName, gugunCode, sidoCode) => {
  const { data } = await defaultInstance(
    `/shelters?breedName=${breedName}&gugunCode=${gugunCode}&sidoCode=${sidoCode}`
  );
  return data;
};

/**
 * GET : 보호소 상세정보
 * @param {*} shelterId
 * @returns 보호소 상세 정보 데이터
 */
export const getShelterDetail = async shelterId => {
  const { data } = await defaultInstance(`/shelters/${shelterId}`);
  return data;
};

/**
 * GET : 시도 리스트
 * @returns [] : 시도 데이터 리스트
 */
export const getSidoList = async () => {
  const { data } = await defaultInstance('/shelters/sidos');
  return data;
};

/**
 * GET : 구군 리스트
 * @param {string} sidoCode sidoCode에 따른
 * @returns [] : 구군 데이터 리스트
 */
export const getGugunList = async sidoCode => {
  const { data } = await defaultInstance(`/shelters/guguns?sidoCode=${sidoCode}`);
  return data;
};

/**
 * GET : MBTI 결과에 따른 견종 결과 데이터
 * @param {string} mbtiId ex) infp,isfp ...
 * @returns 견종 결과 테이터 -> breedName,files,desc,title,mbti
 */
export const getMbtiBreedInfo = async mbtiId => {
  const { data } = await defaultInstance(`/shelters/mbtis/${mbtiId}`);
  return data;
};

// POST

/**
 * 보호소 인증 후 등록
 * @param {string} code
 * @param {string} name
 * @returns
 */
export const createShelter = async (code, name) => {
  const res = await authInstance(`/shelters?code=${code}&name=${name}`);
  return res;
};

export const updateShelter = async (formdata, shelterId) => {
  const res = await authInstance(`/shelters/${shelterId}`, formdata, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};
