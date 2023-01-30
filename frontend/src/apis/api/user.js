import { authInstance } from 'apis/utils';

// GET

/**
 * GET : 유저의 꾹 리스트
 * @returns
 */
export const getFavListListOfUser = async () => {
  const { data } = await authInstance.get('/user/favs');
  return data;
};

// POST

/**
 * POST : 유저 꾹 리스트에 보호 동물 객체 추가
 * @param {*} protectId
 * @returns 성공 , 실패 여부
 */
export const insertFavProtect = async protectId => {
  const res = await authInstance.post(`/user/favs/${protectId}`);
  return res;
};

// DELETE

/**
 * DELETE : 유저 꾹 리스트에 보호 동물 객체 제거
 * @param {*} protectId
 * @returns
 */
export const deleteFavProtect = async protectId => {
  const res = await authInstance.delete(`/user/favs/${protectId}`);
  return res;
};
