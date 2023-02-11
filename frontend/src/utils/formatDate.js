export const formatDate = date => {
  return `${date[0]}년 ${date[1]}월 ${date[2]}일`;
};

export const formatTime = time => {
  return `${time} : 00 ~ ${time + 1} : 00`;
};
