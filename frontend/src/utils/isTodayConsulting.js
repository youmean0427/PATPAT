export const isTodayConsulting = (item, year, month, date) => {
  if (item.consultingDate[0] === year && item.consultingDate[1] === month && item.consultingDate[2] === date)
    return true;
  else return false;
};
