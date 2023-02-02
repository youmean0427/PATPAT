import {
  mbtiResultList,
  searchBreedCountPerRegion,
  searchAllShelters,
  searchAllBreeds,
  searchAllGuguns,
  shelterDetail,
} from 'mocks/data/shelter';
import { rest } from 'msw';

export const shelter = [
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/mbti/:mbtiId`, (req, res, ctx) => {
    const { mbtiId } = req.params;
    const resultItem = mbtiResultList.find(item => item.mbti === mbtiId);
    return res(ctx.status(200), ctx.json(resultItem));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/mbti/count/:breedId`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(searchBreedCountPerRegion));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/shelters`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(searchAllShelters));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/breeds`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(searchAllBreeds));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/guguns`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(searchAllGuguns));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/:shelterId`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(shelterDetail));
  }),
];
