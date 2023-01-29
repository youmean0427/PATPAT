import { rest } from 'msw';
import { mbtiResultList } from 'mocks/data/mbti';

export const mbti = [
  rest.get(`${process.env.REACT_APP_API_URL}/mbti/:result`, (req, res, ctx) => {
    const { result } = req.params;
    const resultItem = mbtiResultList.find(item => item.mbti === result);
    return res(ctx.status(200), ctx.json(resultItem));
  }),
];
