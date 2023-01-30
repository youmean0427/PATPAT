import axios from 'axios';

export const getMissingDogsAPI = async () => {
  const { data } = await axios.get('/report/missing');
  return data;
};
