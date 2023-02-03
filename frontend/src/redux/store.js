import { configureStore } from '@reduxjs/toolkit';
import userReducer from './consulting';

export default configureStore({
  reducer: {
    user: userReducer,
  },
});
