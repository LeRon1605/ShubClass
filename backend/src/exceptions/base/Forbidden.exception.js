import HttpException from './http.exception.js';

class ForbiddenException extends HttpException {
    constructor(message) {
        super(message, 403);
    }
}

export default ForbiddenException;