import 'dotenv/config';
import express from 'express';

import routers from './api/index.js';
import {
    NotFoundMiddleware,
    ExceptionMiddleware,
    AuthenticationMiddleware
} from './middlewares/index.js';
import { Swagger } from './services/index.js';

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// @ts-ignore
app.use('/api-docs', Swagger());
app.use(AuthenticationMiddleware);
app.use('/api', routers);
app.use(ExceptionMiddleware);
app.use(NotFoundMiddleware);

app.listen(process.env.PORT || 3000, () => {
    console.log(`[Info]: Server listening on port ${process.env.PORT || 3000}`);
});
