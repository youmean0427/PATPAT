import { boardReviewList, myBoardListAll } from 'mocks/data/board';
import { rest } from 'msw';

export const board = [
  rest.get(`${process.env.REACT_APP_API_URL}/boards`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(boardReviewList));
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/boards/me`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(myBoardListAll));
  }),
];
