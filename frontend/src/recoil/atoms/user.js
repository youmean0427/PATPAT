import { atom } from 'recoil';

export const userInfoAtom = atom({
  key: 'recoilUserState',
  default: {},
});

export const isLoginAtom = atom({
  key: 'recoilIsLogin',
  default: false,
});
