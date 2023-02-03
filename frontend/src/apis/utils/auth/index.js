import { defaultInstance } from 'apis/utils/index';
import { useRecoilState } from 'recoil';
import { isLoginState } from 'recoil/atoms/user';

export const login = async (provider, code) => {
  try {
    const res = await defaultInstance.get(`http://i8e104.p.ssafy.io:8081/user/login/${provider}?code=${code}`);
    const accessToken = res.data.tokenDto.accessToken;
    const refreshToken = res.data.tokenDto.refreshToken;
    const userInfo = res.data.userDto;

    localStorage.setItem('accessToken', `Bearer ${accessToken}`);
    localStorage.setItem('refreshToken', `Bearer ${refreshToken}`);
    localStorage.setItem('isLogin', true);
    localStorage.setItem(
      'user',
      JSON.stringify({
        userId: userInfo.userid,
        username: userInfo.nickname,
        thumbnail: userInfo.profileImage,
      })
    );
  } catch (e) {
    console.log(e);
  }
};

export const logout = async () => {
  const refresh = localStorage.getItem('refreshToken');
  const access = localStorage.getItem('accessToken');
  const res = await defaultInstance.get(`${process.env.REACT_APP_API_URL}/user/logout`, {
    headers: {
      RefreshToken: refresh,
      AccessToken: access,
    },
  });
  if (res.data.result === 'success') {
    localStorage.removeItem('isLogin');
    localStorage.removeItem('user');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('accessToken');
  } else {
    // 로그아웃에 실패한 경우
  }
};
