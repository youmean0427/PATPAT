import { protectListSortedByEuthanasia, protectListSortedByLatest } from 'mocks/data/protect';
import { rest } from 'msw';

export const protect = [
  rest.get(`${process.env.REACT_APP_API_URL}/protects`, (req, res, ctx) => {
    const code = req.url.searchParams.get('code');
    if (code === 0) {
      return res(ctx.status(200), ctx.json(protectListSortedByEuthanasia));
    }
    return res(ctx.status(200), ctx.json(protectListSortedByLatest));
  }),
];
