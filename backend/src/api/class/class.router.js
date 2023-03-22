import express from 'express';
import ClassController from "./class.controller.js";
import { ValidationMiddleware } from '../../middlewares/index.js';
import { ClassCreateScheme, ClassUpdateScheme } from './dtos/index.js';
import APP_CONSTANT from '../../shared/app.constant.js';

const router = express.Router();

router.get('/', ClassController.getAllClasses)
      .post('/', ValidationMiddleware(ClassCreateScheme, APP_CONSTANT.REQUEST_BODY), ClassController.createClass)
      .put('/:id', ValidationMiddleware(ClassUpdateScheme, APP_CONSTANT.REQUEST_BODY), ClassController.updateClass)
      .delete('/:id', ClassController.deleteClass);
export default router;