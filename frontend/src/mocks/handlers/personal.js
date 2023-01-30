import { persinalDogList } from 'mocks/data/personal';
import { rest } from 'msw';

export const personal = [
  rest.get(`${process.env.REACT_APP_API_URL}/report/personal?`, (req, res, ctx) => {
    const code = req.url.searchParams.get('code');
    if (code === 0) {
      return res(ctx.status(200), ctx.json(persinalDogList));
    }
    return res(ctx.status(200), ctx.json(persinalDogList));
  }),
];
