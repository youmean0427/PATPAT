import { atom } from 'recoil';

export const sidoState = atom({
  key: 'recoilSidoState',
  default: { code: '11', name: '서울특별시' },
});

export const gugunState = atom({
  key: 'recoilGugunState',
  default: {},
});
