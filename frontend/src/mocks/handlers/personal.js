import { persinalDogList } from 'mocks/data/personal';
import { rest } from 'msw';

export const personal = [
  rest.get(`${process.env.REACT_APP_API_URL}/reports/personals`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(persinalDogList));
  }),
];
