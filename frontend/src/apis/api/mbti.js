import { defaultInstance } from 'apis/utils';
import { data } from 'jquery';

export const getMbtiCount = async () => {
  const { data } = await defaultInstance.get('shelters/mbti/count');
  return data;
};

export const increaseMbtiCount = async () => {
  const res = await defaultInstance.get(`shelters/mbti/add/count`);
  return res;
};
