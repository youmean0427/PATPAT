import { createSlice } from '@reduxjs/toolkit';

export const shelterSlice = createSlice({
  name: 'shelter',
  initialState: { value: { resIsShelter: false, resShelterId: '', resUserName: '' } },
  reducers: {
    setShelter: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { setShelter } = shelterSlice.actions;

export default shelterSlice.reducer;
