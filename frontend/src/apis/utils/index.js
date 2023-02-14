import axios from 'axios';

const BASE_URL = process.env.REACT_APP_API_URL;

const axiosApi = baseURL => {
  const instance = axios.create({
    baseURL,
    withCredentials: true,
  });
  return instance;
};

const axiosAuthApi = baseURL => {
  const instance = axios.create({
    baseURL,
    withCredentials: true,
  });
  instance.interceptors.request.use(
    config => {
      const access_token = localStorage.getItem('accessToken');
      if (access_token) {
        config.headers.AccessToken = 'Bearer ' + access_token;
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  instance.interceptors.response.use(
    response => {
      return response;
    },

    async error => {
      const {
        config,
        response: { status },
      } = error;
      if (status === 401) {
        if (error.response.data.code === '003') {
          const originalRequest = config;
          const refreshToken = localStorage.getItem('refreshToken');
          // token refresh 요청
          const data = await authInstance.get(`${process.env.REACT_APP_API_URL}/user/refresh`, {
            headers: { RefreshToken: `Bearer ${refreshToken}` },
          });
          // 요청 후 새롭게 받은 access token과 refresh token 을 다시 저장
          // localStorage에도 변경 해야하고 현재 request의 header도 변경
          const {
            data: { accessToken: newAccessToken, refreshToken: newRefreshToken },
          } = data;
          localStorage.setItem('accessToken', newAccessToken);
          localStorage.setItem('refreshToken', newRefreshToken);
          originalRequest.headers.AccessToken = `Bearer ${newAccessToken}`;
          return authInstance(originalRequest);
        }
      }
      return Promise.reject(error);
    }
  );
  return instance;
};

export const defaultInstance = axiosApi(BASE_URL);
export const authInstance = axiosAuthApi(BASE_URL);
