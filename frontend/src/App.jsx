import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from './components/Common/Header/Header';
import styles from 'assets/styles/App.module.scss';

const App = () => {
  return (
    <div className={styles.app}>
      <Header />
      <main className={styles['app-main']}>
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default App;
