import { atom } from 'recoil';

export const selectSidoState = atom({
  key: 'recoilSidoState',
  default: { sidoCode: null, name: null },
});

export const selectGugunState = atom({
  key: 'recoilGugunState',
  default: { gugunCode: null, name: null },
});

export const selectBreedState = atom({
  key: 'recoilBreedState',
  default: { breedId: null, name: null },
});

export const gugunIsDisabledState = atom({
  key: 'recoilGugunIsDisabled',
  default: true,
});

export const searchSelterListState = atom({
  key: 'recoilShelterListState',
  default: [],
});

export const searchShelterPageState = atom({
  key: 'recoilShelterPageState',
  default: 1,
});

export const currentYearMonthState = atom({
  key: 'recoilCurrentYear',
  default: { month: 2, year: 2023 },
});
