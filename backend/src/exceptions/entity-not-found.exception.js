import NotFoundException from './base/not-found.exception.js';
class EntityNotFoundException extends NotFoundException {
    constructor(name, id) {
        super(`${name} with id '${id}' doesn't exist.`);
    }
}

export default EntityNotFoundException;