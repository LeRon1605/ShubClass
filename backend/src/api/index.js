import { Router } from "express";
import { HomeRoute } from './home/index.js';
import { ClassRoute } from './class/index.js'

const router = new Router();

router.use('/', HomeRoute);
router.use('/classes', ClassRoute);

export default router;