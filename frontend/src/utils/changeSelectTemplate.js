export const changeGugunList = list => {
  return list.map(item => {
    return { value: item.code, label: item.name };
  });
};

export const changeBreedList = list => {
  return list.map(item => {
    return { value: item.breedId, label: item.breedName };
  });
};

export const changeReportBreedList = list => {
  const l = list.map(item => {
    return { value: item.breedId, label: item.breedName };
  });
  return [{ value: 0, label: '전체 보기' }, ...l];
};

export const changeShelterList = list => {
  return list.map(item => {
    return { value: item.idx, label: item.name };
  });
};
