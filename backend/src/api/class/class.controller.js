import ClassService from './class.service.js';
import { ExamService } from '../exam/index.js';
import { ClassCreateDto, ClassUpdateDto } from './dtos/index.js';
import { REQUEST_STATE } from '../../shared/enum/index.js';
import { CacheService } from '../../services/index.js';
class ClassController {
    async getAllClasses(req, res, next) {
        let data = [];
        if (req.session.role == 'Student') {
            data = await CacheService.get(`classes_s${req.session.id}`);
            if (!data) {
                data = await ClassService.getAllClassesOfStudent(req.session.id);
                await CacheService.set(`classes_s${req.session.id}`, data, 30);
            }
        } else {
            data = await CacheService.get(`classes_t${req.session.id}`);
            if (!data) {
                data = await ClassService.getAllClassesOfTeacher(req.session.id);
                await CacheService.set(`classes_t${req.session.id}`, data, 30);
            }
        }
        return res.status(200).json(data);
    }

    async searchClasses(req, res, next) {
        let classes = [];
        if (req.session.role == 'Teacher') {
            classes = await ClassService.getAllClassesByIdForTeacher(
                req.query.id,
                req.session.id
            );
        } else {
            classes = await ClassService.getAllClassesByIdForStudent(
                req.query.id,
                req.session.id
            );
        }
        return res.status(200).json(classes);
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
        const result = await ClassService.updateClass(req.params.id, entity);
        return res.status(200).json(result);
    }

    async deleteClass(req, res, next) {
        await ClassService.deleteClass(req.params.id, req.session.id);
        return res.status(200).json({ message: 'Delete class successfully.' });
    }

    async getAllStudentsInClass(req, res, next) {
        let students = await CacheService.get(`class_student_${req.params.id}`);
        if (!students) {
            students = await ClassService.getAllStudentsByClassId(
                req.params.id
            );
            await CacheService.set(`class_student_${req.params.id}`, students, 60);
        }
        return res.status(200).json(students);
    }

    async makeRequestToClass(req, res, next) {
        const result = await ClassService.makeRequest(
            req.params.id,
            req.session.id
        );
        return res
            .status(200)
            .json({ message: 'Successfully make request to class' });
    }

    async getAllRequestInClass(req, res, next) {
        const requests = await ClassService.getAllRequest(
            req.params.id,
            req.session.id
        );
        return res.status(200).json(requests);
    }

    async removeStudent(req, res, next) {
        await ClassService.removeStudent(
            req.params.id,
            req.session.id,
            req.params.studentId
        );
        return res
            .status(200)
            .json({ message: 'Delete student successfully.' });
    }

    async approveRequest(req, res, next) {
        const request = await ClassService.changeRequestState(
            req.params.id,
            req.session.id,
            req.params.studentId,
            REQUEST_STATE.APPROVED
        );
        return res
            .status(200)
            .json({ message: 'Approved request successfully.' });
    }

    async rejectRequest(req, res, next) {
        const request = await ClassService.changeRequestState(
            req.params.id,
            req.session.id,
            req.params.studentId,
            REQUEST_STATE.REJECT
        );
        return res
            .status(200)
            .json({ message: 'Rejected request successfully.' });
    }

    async getExams(req, res, next) {
        const exams = await ExamService.getAllExamInClass(
            req.params.id,
            req.session
        );
        return res.status(200).json(exams);
    }

    async getStudentInfo(req, res, next) {
        let info = await CacheService.get(`student_summary_c${req.session.id}_s${req.params.id}`);
        if (!info) {
            info = await ClassService.getStudentSummaryInClass(req.session.id, req.params.id);
            await CacheService.set(`student_summary_c${req.session.id}_s${req.params.id}`, info, 3 * 60);
        }
        return res.status(200).json(info);
    }

    async exitClass(req, res, next) {
        await ClassService.exitClass(req.params.id, req.session.id);
        return res.status(200).json({
            message: 'Exit class successfully'
        });
    }
}

export default new ClassController();
