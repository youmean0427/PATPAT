import { customAxios as axios } from 'lib/customAxios';
export const getAbandonedDogsAPI = async () => {
  const { data } = await axios.get('/protect');
  return data;
};

export const getAbandonedReviewAPI = async () => {
  const { data } = await axios.get('/board/3');
  return data;
};
