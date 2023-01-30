import { protect } from './protect';
import { mbti } from './mbti';
import { board } from './board';
import { missing } from './missing';
import { personal } from './personal';

export const handlers = [...mbti, ...protect, ...board, ...missing, ...personal];
