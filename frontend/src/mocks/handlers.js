import { rest } from 'msw';
import { abandonedDog, abandonedReview } from './data';

export const handlers = [
  rest.get('/protects', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedDog));
  }),
  rest.get('/boards/reviews', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(abandonedReview));
  }),
];
