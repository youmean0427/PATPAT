import axios from 'axios';

export const getAbandonedDogsAPI = async () => {
  const { data } = await axios.get('/data/abandoned-dog.json');
  return data;
};

export const getAbandonedReviewAPI = async () => {
  const { data } = await axios.get('/data/abandoned-review.json');
  return data;
};
