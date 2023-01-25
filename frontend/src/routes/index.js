import { createBrowserRouter } from 'react-router-dom';
import MainLayout from 'layouts/MainLayout';
import Home from 'pages/Home/Home';
import Login from 'pages/Login/Login';
import NotFound from 'pages/NotFound/NotFound';
import MbtiLayout from 'layouts/MbtiLayout';
import Result from 'pages/Mbti/Result';
import Start from 'pages/Mbti/Start';
import Test from 'pages/Mbti/Test';

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainLayout />,
    errorElement: <NotFound />, // 라우터에 없는 경로로 이동시 NotFound 컴포넌트 화면에 띄운다.
    children: [
      { index: true, path: '/', element: <Home /> },
      { path: 'home', element: <Home /> },
      { path: 'login', element: <Login /> },
    ],
  },
  {
    path: '/mbti',
    element: <MbtiLayout />,
    errorElement: <NotFound />,
    children: [
      { index: true, element: <Start /> },
      { path: 'test', element: <Test /> },
      { path: 'result', element: <Result /> },
    ],
  },
]);

export default router;
