import { protect } from './protect';
import { board } from './board';
import { shelter } from './shelter';

export const handlers = [...shelter, ...protect, ...board];
