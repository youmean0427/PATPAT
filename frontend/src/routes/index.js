import { createBrowserRouter } from 'react-router-dom';
import App from '../App';
import Home from '../pages/Home/Home';
import Login from '../pages/Login/Login';
import Intro from '../pages/Intro/Intro';
import Vision from '../pages/Intro/Vision';
import Story from '../pages/Intro/Story';
import Statistics from '../pages/Intro/Statistics';
import NotFound from '../pages/NotFound/NotFound';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <NotFound />, // 라우터에 없는 경로로 이동시 NotFound 컴포넌트 화면에 띄운다.
    children: [
      { index: true, path: '/', element: <Home /> },
      { path: 'home', element: <Home /> },
      { path: 'login', element: <Login /> },
      { path: 'intro', element: <Intro /> },
      { path: 'vision', element: <Vision /> },
      { path: 'story', element: <Story /> },
      { path: 'statistics', element: <Statistics /> },
    ],
  },
]);

export default router;
