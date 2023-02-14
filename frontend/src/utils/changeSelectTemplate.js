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

export const changeShelterList = list => {
  return list.map(item => {
    return { value: item.idx, label: item.name };
  });
};
