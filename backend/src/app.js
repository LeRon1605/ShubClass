import 'dotenv/config';
import express from 'express';

import routers from './api/index.js';
import { NotFoundMiddleware } from './middlewares/index.js';

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use('/api', routers);
app.use(NotFoundMiddleware);

app.listen(process.env.PORT || 3000, () => {
    console.log(`[Info]: Server listening on port ${process.env.PORT || 3000}`);
});