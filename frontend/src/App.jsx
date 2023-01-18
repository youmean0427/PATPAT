import { Outlet } from 'react-router-dom';
import Header from './components/Common/Header/Header';
import { Reset } from 'styled-reset';
const App = () => {
  return (
    <>
      <Reset />
      <Header />
      <Outlet />
    </>
  );
};

export default App;
