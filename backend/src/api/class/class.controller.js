import ClassService from './class.service.js';
import { ClassCreateDto, ClassUpdateDto } from './dtos/index.js';
import { REQUEST_STATE } from '../../shared/enum/index.js';
class ClassController {
    async getAllClasses(req, res, next) {
        try {
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
        } catch (error) {
            next(error);
        }
    }

    async createClass(req, res, next) {
        try {
            const entity = ClassCreateDto.toEntity({
                ...req.body,
                teacherId: req.session.id
            });
            const result = await ClassService.createClass(entity);
            return res.status(201).json(result);
        } catch (error) {
            next(error);
        }
    }

    async updateClass(req, res, next) {
        try {
            const entity = ClassUpdateDto.toEntity({
                ...req.body,
                teacherId: req.session.id
            });
            const result = await ClassService.updateClass(
                req.params.id,
                entity
            );
            return res.status(200).json(result);
        } catch (error) {
            next(error);
        }
    }

    async deleteClass(req, res, next) {
        try {
            await ClassService.deleteClass(req.params.id, req.session.id);
            return res.status(200).json({ message: 'Delete class successfully.' });
        } catch (error) {
            next(error);
        }
    }

    async getAllStudentsInClass(req, res, next) {
        try {
            const students = await ClassService.getAllStudentsByClassId(req.params.id);
            return res.status(200).json(students);
        } catch (error) {
            next(error);
        }
    }

    async makeRequestToClass(req, res, next) {
        try {
            const result = await ClassService.makeRequest(req.params.id, req.session.id);
            return res.status(200).json({ message: 'Successfully make request to class' });
        } catch (error) {
            next(error);
        }
    }

    async getAllRequestInClass(req, res, next) {
        try {
            const requests = await ClassService.getAllRequest(req.params.id, req.session.id);
            return res.status(200).json(requests);
        } catch (error) {
            next(error);
        }
    }

    async removeStudent(req, res, next) {
        try {
            await ClassService.removeStudent(req.params.id, req.session.id, req.params.studentId);
            return res.status(200).json({ message: 'Delete student successfully.' });
        } catch (error) {
            next(error);
        }
    }

    async approveRequest(req, res, next) {
        try {
            const request = await ClassService.changeRequestState(req.params.id, req.session.id, req.params.studentId, REQUEST_STATE.APPROVED);
            return res.status(200).json({ message: 'Approved request successfully.' });
        } catch (error) {
            next(error);
        }
    }

    async rejectRequest(req, res, next) {
        try {
            const request = await ClassService.changeRequestState(req.params.id, req.session.id, req.params.studentId, REQUEST_STATE.REJECT);
            return res.status(200).json({ message: 'Rejected request successfully.' });
        } catch (error) {
            next(error);
        }
    }
}

export default new ClassController();
