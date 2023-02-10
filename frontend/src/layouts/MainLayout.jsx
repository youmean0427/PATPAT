import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from 'components/Common/Header/Header';
import styles from './MainLayout.module.scss';
import Container from 'containers/Container';
import ScrollToTop from 'routes/ScrollToTop';
import useModal from 'hooks/useModal';
import EditInfoModal from 'components/Common/Modal/shelters/EditInfoModal';
import EnrollShelterModal from 'components/Common/Modal/shelters/EnrollShelterModal';

const MainLayout = () => {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  return (
    <div className={styles.layout}>
      <ScrollToTop />
      <Header handleClickModalOpen={handleClickModalOpen} />
      <main className={styles['layout-main']}>
        <Container>
          <Outlet />
        </Container>
      </main>
      {isOpen && <EnrollShelterModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} />}
      <Footer />
    </div>
  );
};

export default MainLayout;
