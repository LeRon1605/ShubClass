import { CacheService } from '../../services/index.js';
import { ExamCreateDto } from './dto/index.js';
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

    async getExamQuestion(req, res, next) {
        let questions = await CacheService.get(`exam_question_e${req.params.id}_u${req.session.id}`);
        if (!questions) {
            questions = await ExamService.getQuestion(
                req.params.id,
                req.session
            );
            await CacheService.set(`exam_question_e${req.params.id}_u${req.session.id}`, questions, 3 * 60)
        }
        return res.status(200).json(questions);
    }

    async getExamResult(req, res, next) {
        let result = await CacheService.get(`exam_result_e${req.params.id}_u${req.session.id}`);
        if (!result) {
            result = await ExamService.getResult(req.params.id, req.session);
            await CacheService.set(`exam_result_e${req.params.id}_u${req.session.id}`, result, 3 * 60);
        }
        return res.status(200).json(result);
    }

    async getExamResultOfStudent(req, res, next) {
        const result = await ExamService.getStudentResultInExam(
            req.params.id,
            req.params.studentId
        );
        return res.status(200).json(result);
    }
}

export default new ExamController();
