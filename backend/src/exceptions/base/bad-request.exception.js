import HttpException from './http.exception.js';

class BadRequest extends HttpException {
    constructor(message) {
        super(message, 400);
    }
}

export default BadRequest;