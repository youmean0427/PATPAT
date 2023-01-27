import { customAxios as axios } from 'lib/customAxios';
export const getMbtiResultAPI = async result => {
  const { data } = await axios.get(`/mbti/${result}`);
  return data;
};
