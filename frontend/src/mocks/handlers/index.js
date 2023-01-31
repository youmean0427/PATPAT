import { protect } from './protect';
import { board } from './board';
import { missing } from './missing';
import { personal } from './personal';
import { mbti } from './mbti';

export const handlers = [...mbti, ...protect, ...board, ...missing, ...personal];
