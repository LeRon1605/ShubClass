import ForbiddenException from './base/Forbidden.exception.js';
class EntityForbiddenAccessException extends ForbiddenException {
    constructor(name, id) {
        super(`You are not allowed to access ${name} with id '${id}'.`);
    }
}

export default EntityForbiddenAccessException;