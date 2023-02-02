import axios from 'axios';

const BASE_URL = process.env.REACT_APP_API_URL;

const axiosApi = baseURL => {
  const instance = axios.create({
    baseURL,
    headers: {
      withCredentials: true,
    },
  });
  return instance;
};

const axiosAuthApi = baseURL => {
  const access_token = localStorage.getItem('ACCESS_TOKEN');
  const instance = axios.create({
    baseURL,
    headers: {
      withCredentials: true,
    },
  });
  instance.defaults.headers.common.Authorization = `Bearer ${access_token}`;
  instance.interceptors.request.use(config => {
    const access_token = localStorage.getItem('ACCESS_TOKEN');
    config.headers.Authorization = 'Bearer ' + access_token;
    return config;
  });
  return instance;
};

export const defaultInstance = axiosApi(BASE_URL);
export const authInstance = axiosAuthApi(BASE_URL);
