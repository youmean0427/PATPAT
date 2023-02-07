import { Box, Typography } from '@mui/material';
import React from 'react';
import ModalFrame from '../ModalFrame';

const EditInfoModal = ({ isOpen, handleClickModalClose }) => {
  return (
    <ModalFrame isOpen={isOpen} handleClickModalClose={handleClickModalClose}>
      <form></form>
    </ModalFrame>
  );
};
export default EditInfoModal;
