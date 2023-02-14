import { myFavoriteDogList } from 'mocks/data/user';
import { rest } from 'msw';

export const user = [
  rest.get(`${process.env.REACT_APP_API_URL}/user/favorite`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(myFavoriteDogList));
  }),
];
