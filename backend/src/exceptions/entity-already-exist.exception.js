import BadRequest from './base/bad-request.exception.js';
class EntityAlreadyExistException extends BadRequest {
    constructor(name, id) {
        super(`${name} with id '${id}' already exist.`);
    }
}

export default EntityAlreadyExistException;