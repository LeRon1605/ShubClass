import { fileURLToPath } from 'url';
import { dirname } from 'path';

export default function fileDirName(path) {
    const __filename = fileURLToPath(path);
    const __dirname = dirname(__filename);
    return { __dirname, __filename };
}
