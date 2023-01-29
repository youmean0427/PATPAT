import { defaultInstance } from 'apis/utils';

/**
 *
 * @param {int} code 0 : 안락사 , 1 : 최신순
 * @param {int} limit 리스트로 가져올 item 개수
 * @param {int} offset skip 할 개수
 * @returns [] : code에 따른 Item을 가진 배열
 */
export const getProtectList = async (code, limit, offset) => {
  const { data } = await defaultInstance.get(`/protects?code=${code}&limit=${limit}&offset=${offset}`);
  return data;
};
