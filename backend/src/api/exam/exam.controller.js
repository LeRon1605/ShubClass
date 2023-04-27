import jsonwebtoken from 'jsonwebtoken';
import { ExamCreateDto } from './dto/index.js';
import ExamService from './exam.service.js';

const JWT_KEY = process.env.JWT_KEY;

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

    async startDoingExam(req, res, next) {
        const examId = req.params.id;
        const userId = req.session.id;

        await ExamService.startDoingExam(examId, userId);
        return res
            .status(200)
            .json({ message: 'Start doing exam successfully.' });
    }

    async getExamQuestion(req, res, next) {
        const questions = await ExamService.getQuestion(
            req.params.id,
            req.session
        );
        return res.status(200).json(questions);
    }
}

export default new ExamController();
