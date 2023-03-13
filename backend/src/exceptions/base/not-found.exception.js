import HttpException from './Http.exception.js';

class NotFoundException extends HttpException {
    constructor(message) {
        super(message, 404);
    }
}

export default NotFoundException;