import { atom } from 'recoil';

export const userInfoState = atom({
  key: 'recoilUserState',
  default: {},
});

export const isLoginState = atom({
  key: 'recoilIsLogin',
  default: false,
});

export const myShelterIdState = atom({
  key: 'recoilMyShelterId',
  default: 0,
});
