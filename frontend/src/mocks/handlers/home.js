import { rest } from 'msw';
import { abandonedDog, abandonedReview } from '../data/home';

export const home = [
  rest.get(`${process.env.REACT_APP_SERVER_URL}/protect`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedDog));
  }),
  rest.get(`${process.env.REACT_APP_SERVER_URL}/board/3`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedReview));
  }),
];
