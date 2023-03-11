import morgan from 'morgan';
import path from 'path';
import fs from 'fs';
import fileDirName from '../helpers/url.helper.js';

const accessLogStream = fs.createWriteStream(path.join(fileDirName(import.meta.url).__dirname, '/data/access.log'), { flags: 'a' })

export default morgan('combined', {
    stream: accessLogStream
});