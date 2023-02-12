import { atom } from 'recoil';

export const enrollProtectFormDataListState = atom({
  key: 'recoilProtectFormDataList',
  default: [],
});

export const enrollProtectFormComponentState = atom({
  key: 'recoilProtectFormComponentList',
  default: [],
});
