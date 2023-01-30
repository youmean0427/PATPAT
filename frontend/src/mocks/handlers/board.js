import { boardReviewList } from 'mocks/data/board';
import { rest } from 'msw';

export const board = [
  rest.get(`${process.env.REACT_APP_API_URL}/boards`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(boardReviewList));
  }),
];
