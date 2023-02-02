import { defaultInstance } from 'apis/utils/index';

export const login = async (type, code) => {
  try {
    const res = await defaultInstance.get(`http://i8e104.p.ssafy.io:8081/user/login/${type}?code=${code}`);
    const accessToken = res.data.tokenDto.accessToken;
    const refreshToken = res.data.tokenDto.refreshToken;
    const userInfo = res.data.userDto;

    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('refreshToken', refreshToken);
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

export const logout = () => {
  localStorage.removeItem('isLogin');
  localStorage.removeItem('user');
  localStorage.removeItem('refreshToken');
  localStorage.removeItem('accessToken');
};
