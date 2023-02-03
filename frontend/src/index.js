import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './routes';
import 'assets/styles/index.scss';
import { CircularProgress } from '@mui/material';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { worker } from 'mocks/worker';
import { RecoilRoot } from 'recoil';
if (process.env.NODE_ENV === 'development') {
  worker.start();
}

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 0,
      useErrorBoundary: true,
    },
    mutations: {
      useErrorBoundary: true,
    },
  },
});
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <RecoilRoot>
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} fallbackElement={<CircularProgress />} />
    </QueryClientProvider>
  </RecoilRoot>
);
