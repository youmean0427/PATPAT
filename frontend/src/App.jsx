import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from './components/Common/Header/Header';
import styles from 'App.module.scss';
import SideMenu from 'components/Common/SideMenu/SideMenu';

const App = () => {
  return (
    <div className={styles.app}>
      <Header />
      <SideMenu />
      <main className={styles['app-main']}>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default App;
