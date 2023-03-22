import ForbiddenException from './base/Forbidden.exception.js';
class EntityForbiddenDeleteException extends ForbiddenException {
    constructor(name, id) {
        super(`You are not allowed to delete ${name} with id '${id}'.`);
    }
}

export default EntityForbiddenDeleteException;