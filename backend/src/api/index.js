import express from 'express';
import { ClassRoute } from './class/index.js';
import { AccountRoute } from './account/index.js';
import { ExamRoute } from './exam/index.js';

const router = express.Router();

router.use('/classes', ClassRoute);
router.use('/accounts', AccountRoute);
router.use('/exams', ExamRoute)

export default router;
