import { useState } from 'react';

export default function useModal() {
  const [isOpen, setIsOpen] = useState(false);

  const handleClickModalOpen = () => {
    setIsOpen(true);
    console.log(isOpen);
  };

  const handleClickModalClose = () => {
    setIsOpen(false);
    console.log(isOpen);
  };

  return [isOpen, handleClickModalOpen, handleClickModalClose];
}
