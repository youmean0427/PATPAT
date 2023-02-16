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
import SearchVolunteer from 'pages/Volunteer/SearchVolunteer';
import MyPage from 'pages/MyPage/MyPage';
import MyFamily from 'pages/MyPage/MyFamily/MyFamily';
import MyMissing from 'components/MyPage/MyMissing';
import MyFavorite from 'pages/MyPage/MyFavorite/MyFavorite';
import MyBoard from 'pages/MyPage/MyBoard/MyBoard';
import MyVolunteer from 'pages/MyPage/MyVolunteer/MyVolunteer';
import MyConsulting from 'pages/MyPage/MyConsulting/MyConsulting';
import Report from 'pages/Report/Report';
import MoreInfo from 'pages/Mbti/MoreInfo';
import MissingDogDetail from 'pages/Report/MissingDog/MissingDogDetail';
import ReportCreate from 'pages/Report/Create/ReportCreate';
import KakaoLogin from 'pages/Login/SNSLogin/Kakao/KakaoLogin';
import NaverLogin from 'pages/Login/SNSLogin/Naver/NaverLogin';
import GoogleLogin from 'pages/Login/SNSLogin/Google/GoogleLogin';
import Shelter from 'pages/Shelter/Shelter';
import SearchShelter from 'pages/SearchShelter/SearchShelter';
import PersonalDogDetail from 'pages/Report/PersonalDog/PersonalDogDetail';
import ShelterIntro from 'pages/Shelter/ShelterIntro/ShelterIntro';
import ShelterProtect from 'pages/Shelter/ShelterProtect/ShelterProtect';
import ShelterVolunteer from 'pages/Shelter/ShelterVolunteer/ShelterVolunteer';
import ShelterConsulting from 'pages/Shelter/ShelterConsulting/ShelterConsulting';
import ShelterProtectEnroll from 'pages/Shelter/ShelterProtect/ShelterProtectEnroll';
import ProtectsDetail from 'components/Common/Protects/ProtectsDetail';
import { getBreedsList } from 'apis/api/shelter';
import ReportPersonalDogUpdate from 'pages/Report/Update/PersonalDog/ReportPersonalDogUpdate';
import ReportMissingDogUpdate from 'pages/Report/Update/MissingDog/ReportMissingDogUpdate';
import Community from 'pages/Community/Community';
import AdoptionReview from 'pages/Community/AdoptionReview/AdoptionReview';
import FreeShare from 'pages/Community/FreeShare/FreeShare';
import Information from 'pages/Community/Information/Information';
import CommunityRegist from 'pages/Community/CommunityRegist/CommunityRegist';
import CommunityUpdate from 'pages/Community/CommunityUpdate/CommunityUpdate';
import MoreProduct from 'pages/Home/MoreProduct';

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainLayout />,
    errorElement: <NotFound />, // 라우터에 없는 경로로 이동시 NotFound 컴포넌트 화면에 띄운다.
    children: [
      { index: true, path: '/', element: <Home /> },
      { path: 'intro', element: <Intro /> },
      { path: 'statistics', element: <Statistics /> },
      { path: 'vision', element: <Vision /> },
      { path: 'volunteer', element: <Volunteer /> },
      { path: 'products', element: <MoreProduct /> },
      { path: 'mypage/missing/:id', element: <MyMissing /> },
      {
        path: 'mypage',
        element: <MyPage />,
        children: [
          { path: 'missing', element: <MyFamily /> },
          { path: 'favorite', element: <MyFavorite /> },
          { path: 'boards', element: <MyBoard /> },
          { path: 'volunteer', element: <MyVolunteer /> },
          { path: 'consulting', element: <MyConsulting /> },
        ],
      },
      { path: 'volunteer/search', element: <SearchVolunteer /> },
      { path: 'report', element: <Report /> },
      { path: 'shelter/search', element: <SearchShelter /> },
      { path: 'protects/:id', element: <ProtectsDetail /> },
      {
        path: 'shelter/:shelterId',
        element: <Shelter />,
        children: [
          { path: 'intro', element: <ShelterIntro /> },
          { path: 'protect', element: <ShelterProtect /> },
          {
            path: 'protect/enroll',
            element: <ShelterProtectEnroll />,
            loader: async () => {
              return getBreedsList();
            },
          },
          { path: 'volunteer', element: <ShelterVolunteer /> },
          { path: 'consulting', element: <ShelterConsulting /> },
        ],
      },
      {
        path: 'community',
        element: <Community />,
        children: [
          { path: 'adoption', element: <AdoptionReview /> },
          { path: 'share', element: <FreeShare /> },
          { path: 'info', element: <Information /> },
        ],
      },
      { path: 'community/regist', element: <CommunityRegist /> },
      { path: 'community/update', element: <CommunityUpdate /> },
      { path: 'report/missing/:id', element: <MissingDogDetail /> },
      { path: 'report/personal/:id', element: <PersonalDogDetail /> },
      { path: 'report/personal/:id/update', element: <ReportPersonalDogUpdate /> },
      { path: 'report/missing/:id/update', element: <ReportMissingDogUpdate /> },
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
      { path: 'naver', element: <NaverLogin /> },
      { path: 'google', element: <GoogleLogin /> },
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
