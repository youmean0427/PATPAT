import axios from 'axios';

export const getAbandonedDogsAPI = async () => {
  const { data } = await axios.get('/protects');
  return data;
};

export const getAbandonedReviewAPI = async () => {
  const { data } = await axios.get('/boards/reviews');
  return data;
};
