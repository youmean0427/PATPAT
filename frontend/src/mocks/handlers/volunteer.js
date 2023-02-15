import { volunteerReservationList } from 'mocks/data/volunteer';
import { rest } from 'msw';

export const volunteer = [
  rest.get(`${process.env.REACT_APP_API_URL}/volunteers/reservations`, (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(volunteerReservationList));
  }),
];
