import { configureStore } from '@reduxjs/toolkit';
import shelterReducer from './shelter';
import userReducer from './user';

export default configureStore({
  reducer: {
    shelter: shelterReducer,
    user: userReducer,
  },
});
