import { consultingrReservationList } from 'mocks/data/consulting';
import { rest } from 'msw';

export const consulting = [
  rest.get(`${process.env.REACT_APP_API_URL}/consultations`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(consultingrReservationList));
  }),
];
