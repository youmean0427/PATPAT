import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './routes';
import 'assets/styles/index.scss';
import { CircularProgress } from '@mui/material';
import registerServiceWorker from './registerServiceWorker';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} fallbackElement={<CircularProgress />} />
  </React.StrictMode>
);
registerServiceWorker();
