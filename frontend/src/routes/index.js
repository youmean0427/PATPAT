import { createBrowserRouter } from 'react-router-dom';
import MainLayout from 'layouts/MainLayout';
import Home from 'pages/Home/Home';
import LoginLayout from 'layouts/LoginLayout';
import Login from 'pages/Login/Login';
import NotFound from 'pages/NotFound/NotFound';
import MbtiLayout from 'layouts/MbtiLayout';
import Result from 'pages/Mbti/Result';
import Start from 'pages/Mbti/Start';
import Test from 'pages/Mbti/Test';
import Intro from 'pages/Intro/Intro';
import Statistics from 'pages/Intro/Statistics';
import Vision from 'pages/Intro/Vision';
import PlainLayout from 'layouts/PlainLayout';
import VideoRoomComponent from 'pages/Consulting/Meeting/components/VideoRoomComponent';
import Waiting from 'pages/Consulting/Waiting/Waiting';
import Volunteer from 'pages/Volunteer/Volunteer';
import Address from 'pages/Volunteer/Address';
import MyPage from 'pages/MyPage/MyPage';
import Report from 'pages/Report/Report';
import MoreInfo from 'pages/Mbti/MoreInfo';
import Shelter from 'pages/Shelters/Shelters';
import InfoMain from 'pages/Community/InfoMain';
import InfoDetail from 'components/Community/Info/InfoDetail';
import AdoptionReviewMain from 'pages/Community/AdoptionReviewMain';
import FreeShareMain from 'pages/Community/FreeShareMain';
import FreeShareDetail from 'components/Community/FreeShare/FreeShareDetail';
import MissingDogDetail from 'pages/Report/MissingDog/MissingDogDetail';
import ReportCreate from 'pages/Report/ReportCreate';
import KakaoLogin from 'pages/Login/SNSLogin/Kakao/KakaoLogin';

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
      { path: 'mypage', element: <MyPage /> },
      { path: 'volunteer/address', element: <Address /> },
      { path: 'report', element: <Report /> },
      { path: 'shelters', element: <Shelter /> },
      { path: 'community/info', element: <InfoMain /> },
      { path: 'community/infodetail/:id', element: <InfoDetail /> },
      { path: 'community/adoptionreview', element: <AdoptionReviewMain /> },
      { path: 'community/freeshare', element: <FreeShareMain /> },
      { path: 'community/freesharedetail/:id', element: <FreeShareDetail /> },
      { path: 'report/missing/:id', element: <MissingDogDetail /> },
      { path: 'report/create', element: <ReportCreate /> },
    ],
  },
  {
    path: '/login',
    element: <LoginLayout />,
    errorElement: <giNotFound />,
    children: [
      { index: true, element: <Login /> },
      { path: 'kakao', element: <KakaoLogin /> },
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
      { path: 'result/map', element: <MoreInfo /> },
    ],
  },
  {
    path: '/consulting',
    element: <PlainLayout />,
    errorElement: <NotFound />,
    children: [
      { path: 'meeting', element: <VideoRoomComponent /> },
      { path: 'waiting', element: <Waiting /> },
    ],
  },
]);

export default router;
