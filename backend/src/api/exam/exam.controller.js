import { BadRequestException } from '../../shared/exceptions/index.js';

class ExamController {
    async test(req, res) {
        throw new BadRequestException('Bad');
    }
}

export default new ExamController();