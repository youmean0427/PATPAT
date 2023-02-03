import { femaleMissingDogList, maleMissingDogList, missingDogDetail, missingDogList } from 'mocks/data/missing';
import { rest } from 'msw';

export const missing = [
  rest.get(`${process.env.REACT_APP_API_URL}/reports/missings`, (req, res, ctx) => {
    const gender = req.url.searchParams.get('gender');
    if (gender === '0') {
      return res(ctx.status(200), ctx.json(maleMissingDogList));
    } else if (gender === '1') {
      return res(ctx.status(200), ctx.json(femaleMissingDogList));
    } else {
      return res(ctx.status(200), ctx.json(missingDogList));
    }
  }),
  rest.get(`${process.env.REACT_APP_API_URL}/reports/missings/:userId`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(missingDogList));
  }),

  rest.get(`${process.env.REACT_APP_API_URL}/reports/missings/detail/:missingId`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(missingDogDetail));
  }),

  rest.post(`${process.env.REACT_APP_API_URL}/reports`, (req, res, ctx) => {
    const reportType = req.url.searchParams.get('reportType');
    if (reportType === '0') {
      // console.log('req', req.body);
      missingDogList.push(req.body);
      // console.log('Handler', missingDogList);
      // console.log('ful', missingDogList);
      return res(ctx.status(201));
    }
  }),
];
