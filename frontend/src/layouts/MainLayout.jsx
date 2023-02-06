import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from 'components/Common/Header/Header';
import styles from './MainLayout.module.scss';
import Container from 'containers/Container';
import ScrollToTop from 'routes/ScrollToTop';

const MainLayout = () => {
  return (
    <div className={styles.layout}>
      <ScrollToTop />
      <Header />
      <main className={styles['layout-main']}>
        <Container>
          <Outlet />
        </Container>
      </main>
      <Footer />
    </div>
  );
};

export default MainLayout;
