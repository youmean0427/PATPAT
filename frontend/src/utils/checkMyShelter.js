export const checkMyShelter = (shelterId, userShelterId) => {
  return parseInt(shelterId) === userShelterId;
};

export const isHaveShelter = shelterId => {
  return shelterId !== null;
};
