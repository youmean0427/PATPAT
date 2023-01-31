import { mbtiResultList } from 'mocks/data/shelter';
import { rest } from 'msw';

export const shelter = [
  rest.get(`${process.env.REACT_APP_API_URL}/shelters/mbtis/:mbtiId`, (req, res, ctx) => {
    const { mbtiId } = req.params;
    const resultItem = mbtiResultList.find(item => item.mbti === mbtiId);
    return res(ctx.status(200), ctx.json(resultItem));
  }),
];
