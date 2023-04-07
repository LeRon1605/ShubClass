import BadRequest from './base/bad-request.exception.js';
class EntityAlreadyExistException extends BadRequest {
    constructor(name, id, column = null) {
        super(`${name} with ${column ?? 'id'} '${id}' already exist.`);
    }
}

export default EntityAlreadyExistException;
