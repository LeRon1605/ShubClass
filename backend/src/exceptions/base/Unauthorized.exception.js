import HttpException from './Http.exception.js';

class Unauthorized extends HttpException {
    constructor(message) {
        super(message, 401);
    }
}

export default Unauthorized;