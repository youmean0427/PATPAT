import { createSlice } from '@reduxjs/toolkit';

export const consultingSlice = createSlice({
  name: 'consulting',
  initialState: { value: { resShelterId: '', resUserName: '' } },
  reducers: {
    setConsulting: (state, action) => {
      state.value = action.payload;
    },
  },
});

export const { setConsulting } = consultingSlice.actions;

export default consultingSlice.reducer;
