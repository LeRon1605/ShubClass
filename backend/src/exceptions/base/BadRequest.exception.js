import HttpException from './Http.exception.js';

class BadRequest extends HttpException {
    constructor(message) {
        super(message, 400);
    }
}

export default BadRequest;