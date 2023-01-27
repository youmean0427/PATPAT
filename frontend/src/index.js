import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './routes';
import 'assets/styles/index.scss';
import { CircularProgress } from '@mui/material';
import registerServiceWorker from './registerServiceWorker';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { worker } from 'mocks/worker';
if (process.env.NODE_ENV === 'development') {
  worker.start();
}

const queryClient = new QueryClient();

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <QueryClientProvider client={queryClient}>
    <RouterProvider router={router} fallbackElement={<CircularProgress />} />
  </QueryClientProvider>
);
registerServiceWorker();
