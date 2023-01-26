import { createBrowserRouter } from 'react-router-dom';
import MainLayout from 'layouts/MainLayout';
import Home from 'pages/Home/Home';
import Login from 'pages/Login/Login';
import NaverLogin from 'pages/Login/SNSLogin/Naver/NaverLogin';
import KakaoLogin from 'pages/Login/SNSLogin/Kakao/KakaoLogin';
import NotFound from 'pages/NotFound/NotFound';
import MbtiLayout from 'layouts/MbtiLayout';
import Result from 'pages/Mbti/Result';
import Start from 'pages/Mbti/Start';
import Test from 'pages/Mbti/Test';
import Intro from 'pages/Intro/Intro';
import Statistics from 'pages/Intro/Statistics';
import Vision from 'pages/Intro/Vision';
import PlainLayout from 'layouts/PlainLayout';
import Meeting from 'pages/Consulting/Meeting/Meeting';
import Waiting from 'pages/Consulting/Waiting/Waiting';
import Volunteer from 'pages/Volunteer/Volunteer';
import Address from 'pages/Volunteer/Address';

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainLayout />,
    errorElement: <NotFound />, // 라우터에 없는 경로로 이동시 NotFound 컴포넌트 화면에 띄운다.
    children: [
      { index: true, path: '/', element: <Home /> },
      { path: 'home', element: <Home /> },
      { path: 'intro', element: <Intro /> },
      { path: 'statistics', element: <Statistics /> },
      { path: 'vision', element: <Vision /> },
      { path: 'volunteer', element: <Volunteer /> },
    ],
  },
  {
    path: '/login',
    element: <PlainLayout />,
    errorElement: <giNotFound />,
    children: [
      { index: true, element: <Login /> },
      { path: 'kakao', element: <KakaoLogin /> },
      { path: 'naver', element: <NaverLogin /> },
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
  {
    path: '/consulting',
    element: <PlainLayout />,
    errorElement: <NotFound />,
    children: [
      { path: 'meeting', element: <Meeting /> },
      { path: 'waiting', element: <Waiting /> },
    ],
  },
]);

export default router;
