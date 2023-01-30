import { defaultInstance } from 'apis/utils';

/**
 *
 * @param {string} result
 * @returns mbti에 따른 결과 객체
 */
export const getMbtiResult = async mbti => {
  const { data } = await defaultInstance.get(`/mbti/${mbti}`);
  return data;
};
