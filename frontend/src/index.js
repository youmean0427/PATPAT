import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './routes';
import 'assets/styles/index.scss';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { worker } from 'mocks/worker';
import { RecoilRoot } from 'recoil';
import store from 'redux/store';
import { Provider } from 'react-redux';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import Loading from 'components/Common/Loading';

if (process.env.NODE_ENV === 'development') {
  // worker.start();
}

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 0,
      refetchOnWindowFocus: false,
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
      <Provider store={store}>
        <RouterProvider router={router} fallbackElement={<Loading />} />
      </Provider>
      <ReactQueryDevtools initialIsOpen={true} />
    </QueryClientProvider>
  </RecoilRoot>
);
