import Footer from 'components/Common/Footer/Footer';
import { Outlet } from 'react-router-dom';
import Header from 'components/Common/Header/Header';
import Container from 'containers/Container';
import useModal from 'hooks/useModal';
import EnrollShelterModal from 'components/Common/Modal/shelters/EnrollShelterModal';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import styles from './MainLayout.module.scss';

const MainLayout = () => {
  const [isOpen, handleClickModalOpen, handleClickModalClose] = useModal();
  return (
    <div className={styles.layout}>
      <Header handleClickModalOpen={handleClickModalOpen} />
      <main className={styles['layout-main']}>
        <Container>
          <Outlet />
        </Container>
      </main>
      {isOpen && <EnrollShelterModal isOpen={isOpen} handleClickModalClose={handleClickModalClose} />}
      <ToastContainer position="bottom-center" limit={2} closeButton={false} autoClose={2000} hideProgressBar />
    </div>
  );
};

export default MainLayout;
