import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from 'components/Common/Header/Header';
import styles from './MainLayout.module.scss';
import SideMenu from 'components/Common/SideMenu/SideMenu';
import Container from 'components/Common/Container';
import { useState } from 'react';

const MainLayout = () => {
  const [isLogin, setIsLogin] = useState(false); // eslint-disable-line no-unused-vars
  return (
    <div className={styles.layout}>
      <Header />
      <main className={styles['layout-main']}>
        <Container>
          {isLogin && <SideMenu />}
          <Outlet />
        </Container>
      </main>
      <Footer />
    </div>
  );
};

export default MainLayout;
