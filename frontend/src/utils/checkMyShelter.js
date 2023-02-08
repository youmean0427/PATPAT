export const checkMyShelter = (shelterId, userShelterId) => {
  return parseInt(shelterId) === userShelterId;
};

export const isHaveShelter = () => {
  const shelterId = JSON.parse(localStorage.getItem('user'))?.shelterId;
  return shelterId !== null;
};
