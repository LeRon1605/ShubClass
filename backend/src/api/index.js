import express from 'express';
import { ClassRoute } from './class/index.js';
import { AccountRoute } from './account/index.js';
import { ExamRoute } from './exam/index.js';
import { UserExamRouter } from './user-exams/index.js';
import { UserAnswerRouter } from './user-answers/index.js';

const router = express.Router();

router.use('/classes', ClassRoute);
router.use('/accounts', AccountRoute);
router.use('/exams', ExamRoute);
router.use('/user-exams', UserExamRouter);
router.use('/user-exams/:id/user-answers', UserAnswerRouter);

export default router;
