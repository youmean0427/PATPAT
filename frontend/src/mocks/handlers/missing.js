import { femaleMissingDogList, maleMissingDogList, missingDogList } from 'mocks/data/missing';
import { rest } from 'msw';

export const missing = [
  rest.get(`${process.env.REACT_APP_API_URL}/reports/missings`, (req, res, ctx) => {
    const gender = req.url.searchParams.get('gender');
    if (gender === '0') {
      return res(ctx.status(200), ctx.json(maleMissingDogList));
    } else if (gender === '1') {
      return res(ctx.status(200), ctx.json(femaleMissingDogList));
    } else {
      return res(ctx.status(200), ctx.json(missingDogList));
    }
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/reports/missings/:userId`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(missingDogList));
  }),
];
