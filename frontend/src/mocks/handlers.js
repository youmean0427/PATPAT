import { rest } from 'msw';
import { abandonedDog, abandonedReview } from './data';
import { missingDog } from './missingData';

export const handlers = [
  rest.get('/protects', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedDog));
  }),
  rest.get('/boards/reviews', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedReview));
  }),

  rest.get('/report/missing', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(missingDog));
  }),
];
