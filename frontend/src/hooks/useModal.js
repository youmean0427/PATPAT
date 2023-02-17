import { useState } from 'react';

export default function useModal() {
  const [isOpen, setIsOpen] = useState(false);

  const handleClickModalOpen = () => {
    setIsOpen(true);
  };

  const handleClickModalClose = () => {
    setIsOpen(false);
  };

  return [isOpen, handleClickModalOpen, handleClickModalClose];
}
