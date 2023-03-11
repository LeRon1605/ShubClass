import express from 'express';
import HomeController from "./home.controller.js";

const router = express.Router();

router.get('/', HomeController.greeting);

export default router;