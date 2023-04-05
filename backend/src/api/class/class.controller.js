import ClassService from './class.service.js';
import { ExamService } from '../exam/index.js';
import { ClassCreateDto, ClassUpdateDto } from './dtos/index.js';
import { REQUEST_STATE } from '../../shared/enum/index.js';
class ClassController {
    async getAllClasses(req, res, next) {
        let data = [];
        if (req.session.role == 'Student') {
            data = await ClassService.getAllClassesOfStudent(
                req.session.id
            );
        } else {
            data = await ClassService.getAllClassesOfTeacher(
                req.session.id
            );
        }
        return res.status(200).json(data);
    }

    async createClass(req, res, next) {
        const entity = ClassCreateDto.toEntity({
            ...req.body,
            teacherId: req.session.id
        });
        const result = await ClassService.createClass(entity);
        return res.status(201).json(result);
    }

    async updateClass(req, res, next) {
        const entity = ClassUpdateDto.toEntity({
            ...req.body,
            teacherId: req.session.id
        });
        const result = await ClassService.updateClass(
            req.params.id,
            entity
        );
        return res.status(200).json(result);
    }

    async deleteClass(req, res, next) {
        await ClassService.deleteClass(req.params.id, req.session.id);
        return res.status(200).json({ message: 'Delete class successfully.' });
    }

    async getAllStudentsInClass(req, res, next) {
        const students = await ClassService.getAllStudentsByClassId(req.params.id);
        return res.status(200).json(students);
    }

    async makeRequestToClass(req, res, next) {
        const result = await ClassService.makeRequest(req.params.id, req.session.id);
        return res.status(200).json({ message: 'Successfully make request to class' });
    }

    async getAllRequestInClass(req, res, next) {
        const requests = await ClassService.getAllRequest(req.params.id, req.session.id);
        return res.status(200).json(requests);
    }

    async removeStudent(req, res, next) {
        await ClassService.removeStudent(req.params.id, req.session.id, req.params.studentId);
        return res.status(200).json({ message: 'Delete student successfully.' });
    }

    async approveRequest(req, res, next) {
        const request = await ClassService.changeRequestState(req.params.id, req.session.id, req.params.studentId, REQUEST_STATE.APPROVED);
        return res.status(200).json({ message: 'Approved request successfully.' });
    }

    async rejectRequest(req, res, next) {
        const request = await ClassService.changeRequestState(req.params.id, req.session.id, req.params.studentId, REQUEST_STATE.REJECT);
        return res.status(200).json({ message: 'Rejected request successfully.' });
    }

    async getExams(req, res, next) {
        const exams = await ExamService.getAllExamInClass(req.params.id, req.query.type, req.session);
        return res.status(200).json(exams)
    }
}

export default new ClassController();
