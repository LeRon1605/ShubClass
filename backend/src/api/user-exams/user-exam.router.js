import express from 'express';
import { AuthorizationMiddleware } from '../../middlewares/index.js';
import UserExamController from './user-exam.controller.js';

const router = express.Router();

router
    .post(
        '/:id/submit',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        UserExamController.submit
    )
    .post(
        '/:id',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        UserExamController.startDoingExam
    );

export default router;
