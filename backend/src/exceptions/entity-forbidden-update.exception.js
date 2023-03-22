import Forbidden from './base/Forbidden.exception.js';
class EntityForbiddenUpdateException extends Forbidden {
    constructor(name, id) {
        super(`You are not allowed to update ${name} with id '${id}'.`);
    }
}

export default EntityForbiddenUpdateException;