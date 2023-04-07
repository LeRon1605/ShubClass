import { ExamCreateDto } from './dto/index.js'
import ExamService from './exam.service.js';

class ExamController {
    async createExam(req, res, next) {
        const exam = ExamCreateDto.toEntity(req.body);
        const result = await ExamService.create(exam, req.session.id);
        return res.status(201).json(result);
    }

    async removeExam(req, res, next) {
        await ExamService.remove(req.params.id, req.session.id);
        return res.status(200).json({ message: 'Delete exam successfully.' });
    }
}

export default new ExamController();