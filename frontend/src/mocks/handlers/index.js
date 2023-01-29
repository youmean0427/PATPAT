import { protect } from './protect';
import { mbti } from './mbti';
import { board } from './board';

export const handlers = [...mbti, ...protect, ...board];
