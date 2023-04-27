import jsonwebtoken from 'jsonwebtoken';
import { ExamCreateDto } from './dto/index.js';
import ExamService from './exam.service.js';
import dotenv from 'dotenv';
dotenv.config({ path: '../../.env' });

const JWT_KEY = process.env.JWT_KEY;

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
        const authorizationHeader = req.headers.authorization;
        const userToken = authorizationHeader.substring(7);

        const isTokenValid = jsonwebtoken.verify(userToken, JWT_KEY);
        const examId = isTokenValid.id;

        await ExamService.startDoingExam(req.params.id, examId);
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
