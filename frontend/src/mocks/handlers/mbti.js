import { rest } from 'msw';
import { resultList } from 'mocks/data/mbti';

export const mbti = [
  rest.get(`${process.env.REACT_APP_SERVER_URL}/mbti/:result`, (req, res, ctx) => {
    const { result } = req.params;
    console.log(result);
    const resultItem = resultList.find(item => item.mbti === result);
    console.log(resultItem);
    return res(ctx.status(200), ctx.json(resultItem));
  }),
];
