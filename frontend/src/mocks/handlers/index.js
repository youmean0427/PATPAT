import { protect } from './protect';
import { board } from './board';
import { missing } from './missing';
import { personal } from './personal';
import { shelter } from './shelter';
import { volunteer } from './volunteer';

export const handlers = [...shelter, ...protect, ...board, ...missing, ...personal, ...volunteer];
