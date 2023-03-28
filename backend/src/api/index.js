import express from 'express';
import { HomeRoute } from './home/index.js';
import { ClassRoute } from './class/index.js';
import { AccountRoute } from './account/index.js';

// const router = new Router();
const router = express.Router();

router.use('/', HomeRoute);
router.use('/classes', ClassRoute);
router.use('/accounts', AccountRoute);

export default router;
