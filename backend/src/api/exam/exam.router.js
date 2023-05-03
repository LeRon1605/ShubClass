import express from 'express';
import ExamController from './exam.controller.js';

import {
    ValidationMiddleware,
    AuthorizationMiddleware
} from '../../middlewares/index.js';
import { ExamCreateScheme } from './dto/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router
    .post(
        '/',
        ValidationMiddleware(ExamCreateScheme, APP_CONSTANT.REQUEST_BODY),
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ExamController.createExam
    )
    .delete(
        '/:id',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ExamController.removeExam
    )
    .post(
        '/:id',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        ExamController.startDoingExam
    );

router.get(
    '/:id/questions',
    AuthorizationMiddleware({ type: 'basic' }),
    ExamController.getExamQuestion
);

router
    .get(
        '/:id/result',
        AuthorizationMiddleware({ type: 'basic' }),
        ExamController.getExamResult
    )
    .get(
        '/:id/result/:studentId',
        AuthorizationMiddleware({ type: 'basic' }),
        ExamController.getExamResultOfStudent
    );

export default router;
