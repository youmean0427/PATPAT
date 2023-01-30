import { defaultInstance } from 'apis/utils';

// GET

/**
 * GET : 실종동물 리스트
 * @param {int} code  code==0 전체 실종견 , code==1 해당 유저의 실종공고 리스트 , code==2 견종 성별 필터링 검색
 * @param {int} limit 리스트로 가져올 item 개수
 * @param {int} offset skip 할 개수
 * @returns [] : code에 따른 Item을 가진 배열
 */
export const getMissingList = async (code, limit, offset) => {
  const { data } = await defaultInstance.get(`/report/missing?code=${code}&limit=${limit}&offset=${offset}`);
  return data;
};

export const getPersonalList = async (code, limit, offset) => {
  const { data } = await defaultInstance.get(`/report/personal?code=${code}&limit=${limit}&offset=${offset}`);
  return data;
};
