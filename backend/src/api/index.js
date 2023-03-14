import { Router } from "express";
import { HomeRoute } from './home/index.js';

const router = new Router();

router.use('/', HomeRoute);

export default router;