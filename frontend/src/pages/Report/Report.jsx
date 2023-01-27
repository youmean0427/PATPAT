import Banner from 'components/Banner/Banner';
import Section from 'components/Home/Section';
import MissingDogList from 'components/Report/MissingDogList';
import React from 'react';

export default function Address() {
  return (
    <div>
      <Banner title="실종견 / 임보견" />
      <Section>
        <MissingDogList />
      </Section>
    </div>
  );
}
