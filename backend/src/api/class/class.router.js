import express from 'express';
import ClassController from './class.controller.js';
import { ValidationMiddleware } from '../../middlewares/index.js';
import { ClassCreateScheme, ClassUpdateScheme } from './dtos/index.js';
import { AuthorizationMiddleware } from '../../middlewares/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router
    .get(
        '/',
        AuthorizationMiddleware({ type: 'basic' }),
        ClassController.getAllClasses
    )
    .get(
        '/search',
        AuthorizationMiddleware({ type: 'basic' }),
        ClassController.searchClasses
    )
    .get(
        '/:id',
        AuthorizationMiddleware({ type: 'basic' }),
        ClassController.getById
    )
    .post(
        '/',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ValidationMiddleware(ClassCreateScheme, APP_CONSTANT.REQUEST_BODY),
        ClassController.createClass
    )
    .put(
        '/:id',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ValidationMiddleware(ClassUpdateScheme, APP_CONSTANT.REQUEST_BODY),
        ClassController.updateClass
    )
    .delete(
        '/:id',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ClassController.deleteClass
    );

router
    .get(
        '/:id/students',
        AuthorizationMiddleware({ type: 'basic' }),
        ClassController.getAllStudentsInClass
    )
    .delete(
        '/:id/students',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        ClassController.existClass
    )
    .delete(
        '/:id/students/:studentId',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ClassController.removeStudent
    )
    .get(
        '/:id/students/me',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        ClassController.getStudentInfo
    );

router
    .get(
        '/:id/requests',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ClassController.getAllRequestInClass
    )
    .post(
        '/:id/requests',
        AuthorizationMiddleware({ type: 'role', value: 'Student' }),
        ClassController.makeRequestToClass
    )
    .put(
        '/:id/requests/:studentId',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ClassController.approveRequest
    )
    .delete(
        '/:id/requests/:studentId',
        AuthorizationMiddleware({ type: 'role', value: 'Teacher' }),
        ClassController.rejectRequest
    );

router.get(
    '/:id/exams',
    AuthorizationMiddleware({ type: 'basic' }),
    ClassController.getExams
);

export default router;
