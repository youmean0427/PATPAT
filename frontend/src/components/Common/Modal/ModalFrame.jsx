import { Modal, Box } from '@mui/material';
import React from 'react';
import ModalPortal from './ModalPortal';

const ModalFrame = ({ children, isOpen, handleClickModalClose }) => {
  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 1200,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };
  return (
    <ModalPortal>
      <Modal
        open={isOpen}
        onClose={handleClickModalClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>{children}</Box>
      </Modal>
    </ModalPortal>
  );
};

export default ModalFrame;
