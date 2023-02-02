import React from 'react';

export default function BreedsItem({ item }) {
  const breedName = item;

  return <option value={breedName}>{breedName}</option>;
}
