import express from 'express';
import UserAnswerController from './user-answer.controller.js';
import { AuthorizationMiddleware } from '../../middlewares/index.js';
const router = express.Router();

router.post(
    '/',
    AuthorizationMiddleware({ type: 'role', value: 'Student' }),
    UserAnswerController.doExam
);

export default router;
