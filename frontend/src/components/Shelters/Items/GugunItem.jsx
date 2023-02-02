import React from 'react';

export default function GugunItem({ item }) {
  const name = item;

  return <option value={name}>{name}</option>;
}
