import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import React from 'react';
import { useState } from 'react';
import ModalFrameSmall from '../ModalFrameSmall';
import { createShelter, getAuthShelterList } from 'apis/api/shelter';
import Select from 'react-select';
import { changeShelterList } from 'utils/changeSelectTemplate';
import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import Loading from 'components/Common/Loading';
export default function EnrollShelterModal({ isOpen, handleClickModalClose }) {
  const [code, setCode] = useState();
  const [name, setName] = useState();
  const { data, isLoading } = useQuery(['authShelters'], getAuthShelterList);
  const navigate = useNavigate();
  const queryClient = useQueryClient();
  const { mutate } = useMutation(['createShelter'], data => createShelter(data), {
    onSuccess: ({ data }) => {
      queryClient.invalidateQueries('getUserInfo');
    },
  });

  const handleChangeOnShelter = useCallback(selected => {
    setName(selected.label);
  });
  const handleSubmit = e => {
    e.preventDefault();
    mutate({ shelterCode: code, shelterName: name });
    handleClickModalClose();
  };
  if (isLoading) return;
  return (
    <ModalFrameSmall isOpen={isOpen} handleClickModalClose={handleClickModalClose}>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="코드를 입력하세요" value={code} onChange={e => setCode(e.target.value)} />
        <Select options={changeShelterList(data)} onChange={handleChangeOnShelter} />
        <button>제출</button>
      </form>
    </ModalFrameSmall>
  );
}
