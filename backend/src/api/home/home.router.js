import express from 'express';
import HomeController from './home.controller.js';
import ValidationMiddleware from '../../middlewares/validation-handler.middleware.js';
import { HomeCreateScheme } from './dtos/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router.post(
    '/',
    ValidationMiddleware(HomeCreateScheme, APP_CONSTANT.REQUEST_BODY),
    HomeController.greeting
);

export default router;
