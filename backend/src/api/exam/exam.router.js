import express from 'express';
import ExamController from './exam.controller.js';

const router = express.Router();

router
    .get('/', ExamController.test);

export default router;