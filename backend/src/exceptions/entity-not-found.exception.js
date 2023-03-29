import NotFoundException from './base/not-found.exception.js';
class EntityNotFoundException extends NotFoundException {
    constructor(name, id, column = null) {
        super(`${name} with ${column ?? 'id'} '${id}' doesn't exist.`);
    }
}

export default EntityNotFoundException;
