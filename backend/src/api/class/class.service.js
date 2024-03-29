import sequelize from '../../database/models/index.cjs';
import { Op } from 'sequelize';
import { ClassDto, RequestDto } from './dtos/index.js';
import { REQUEST_STATE } from '../../shared/enum/index.js';
import {
    EntityNotFoundException,
    EntityAlreadyExistException,
    EntityForbiddenUpdateException,
    EntityForbiddenDeleteException,
    BadRequestException,
    EntityForbiddenAccessException,
    NotFoundException
} from '../../shared/exceptions/index.js';
import { StudentDto } from '../account/dtos/student.dto.js';

const { User, Class, Account, StudentClass, UserExam, Exam } = sequelize;
class ClassService {
    async getAllClassesOfTeacher(userId) {
        const user = await User.findByPk(userId);
        const data = await Class.findAll({
            where: {
                teacherId: userId
            }
        });
        return data.map((x) => ClassDto.toDto(x));
    }

    async getAllClassesOfStudent(userId) {
        const user = await User.findByPk(userId);
        if (user == null) {
            throw new EntityNotFoundException('User', userId);
        }
        const data = await StudentClass.findAll({
            where: {
                studentId: userId,
                state: REQUEST_STATE.APPROVED
            },
            include: [
                {
                    model: Class
                }
            ]
        });
        return data.map((x) => ClassDto.toDto(x.Class));
    }

    async getAllClassesByIdForTeacher(classId, teacherId) {
        const classes = await Class.findAll({
            where: {
                id: {
                    [Op.like]: `%${classId}%`
                },
                teacherId: {
                    [Op.not]: teacherId
                }
            }
        });
        return classes.map((x) => ClassDto.toDto(x));
    }

    async getAllClassesByIdForStudent(classId, studentId) {
        const classes = (
            await Class.findAll({
                where: {
                    id: {
                        [Op.like]: `%${classId}%`
                    }
                },
                include: [StudentClass]
            })
        ).filter(
            (x) =>
                !x.StudentClasses.some((x) => x.studentId == studentId) ||
                x.StudentClasses.some(
                    (x) =>
                        x.studentId == studentId &&
                        x.state != REQUEST_STATE.APPROVED
                )
        );

        return classes.map((x) => ClassDto.toDto(x));
    }

    async createClass(newClass) {
        const classEntity = await Class.findByPk(newClass.id);
        if (classEntity != null) {
            throw new EntityAlreadyExistException('Class', newClass.id);
        }

        const teacher = await User.findByPk(newClass.teacherId);
        if (teacher == null) {
            throw new EntityNotFoundException('User', newClass.teacherId);
        }

        return await Class.create(newClass);
    }

    async updateClass(id, newClass) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        const teacher = await User.findByPk(newClass.teacherId);
        if (teacher == null) {
            throw new EntityNotFoundException('User', newClass.teacherId);
        }

        if (teacher.id != classEntity.teacherId) {
            throw new EntityForbiddenUpdateException('Class', classEntity.id);
        }

        await Class.update(
            { ...classEntity, ...newClass },
            {
                where: {
                    id: id
                }
            }
        );

        const dto = ClassDto.toDto(await Class.findByPk(id));
        return dto;
    }

    async deleteClass(id, teacherId) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenDeleteException('Class', id);
        }

        await Class.destroy({
            where: {
                id: id
            }
        });
    }

    async getAllStudentsByClassId(id) {
        const studentClassEntities = await StudentClass.findAll({
            where: {
                classId: id,
                state: REQUEST_STATE.APPROVED
            },
            include: User
        });

        const students = studentClassEntities.map((x) => x.User);

        return students.map((student) => StudentDto.toDto(student));
    }

    async makeRequest(id, studentId) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        const studentClassEntities = await StudentClass.findOne({
            where: {
                classId: id,
                studentId: studentId
            }
        });

        if (studentClassEntities == null) {
            return await StudentClass.create({
                classId: id,
                studentId: studentId,
                state: REQUEST_STATE.PENDING
            });
        } else if (studentClassEntities.state == REQUEST_STATE.REJECT) {
            await StudentClass.update(
                { state: REQUEST_STATE.PENDING },
                {
                    where: {
                        id: id
                    }
                }
            );
            return studentClassEntities;
        } else if (studentClassEntities.state == REQUEST_STATE.APPROVED) {
            throw new BadRequestException('You are already in class.');
        } else {
            throw new BadRequestException('Your request are being reviewed.');
        }
    }

    async getAllRequest(id, teacherId) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenAccessException('Class', id);
        }

        const requests = await StudentClass.findAll({
            where: {
                classId: id,
                state: REQUEST_STATE.PENDING
            },
            include: User
        });

        return requests.map((x) => RequestDto.toDto(x));
    }

    async removeStudent(id, teacherId, studentId) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenAccessException('Class', id);
        }

        const request = await StudentClass.findOne({
            where: {
                studentId: studentId,
                classId: id,
                state: REQUEST_STATE.APPROVED
            }
        });

        if (request == null) {
            throw new NotFoundException('Student are not in class.');
        }

        await StudentClass.destroy({
            where: {
                studentId: studentId,
                classId: id,
                state: REQUEST_STATE.APPROVED
            }
        });
    }

    async changeRequestState(id, teacherId, studentId, state) {
        const classEntity = await Class.findByPk(id);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', id);
        }

        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenAccessException('Class', id);
        }

        const request = await StudentClass.findOne({
            where: {
                studentId: studentId,
                classId: id,
                state: REQUEST_STATE.PENDING
            }
        });

        if (request == null) {
            throw new EntityNotFoundException('Request', studentId);
        }

        await StudentClass.update(
            { state: state },
            {
                where: {
                    studentId: studentId,
                    classId: id
                }
            }
        );
        return request;
    }

    async getStudentSummaryInClass(studentId, classId) {
        const classEntity = await Class.findOne({
            where: {
                id: classId,
            },
            include: [Exam, StudentClass]
        });

        if (classEntity == null) {
            throw new EntityNotFoundException('Class', classId);
        }

        if (!classEntity.StudentClasses.some((x) => x.studentId == studentId)) {
            throw new EntityForbiddenAccessException('Class', classId);
        }

        const userExams = await UserExam.findAll({
            where: {
                studentId: studentId,
                endAt: {
                    [Op.ne]: null
                }
            },
            include: [
                {
                    model: Exam,
                    where: {
                        classId: classId
                    }
                }
            ]
        });

        const average = userExams.map(x => x.points).reduce((acc, element, index, arr) => acc + (element * 1.0 / arr.length), 0);
        const completedExam = new Set(userExams.map(x => x.Exam.id)).size;
        const numberOfExams = classEntity.Exams.length;

        return {
            average: average,
            completedExam: completedExam,
            numberOfExams: numberOfExams
        }
    }

    async exitClass(classId, studentId) {
        const classEntity = await Class.findOne({
            where: {
                id: classId,
            },
            include: [Exam, StudentClass]
        });

        if (classEntity == null) {
            throw new EntityNotFoundException('Class', classId);
        }

        if (!classEntity.StudentClasses.some((x) => x.studentId == studentId && x.state == REQUEST_STATE.APPROVED)) {
            throw new EntityForbiddenAccessException('Class', classId);
        }

        await StudentClass.destroy({
            where: {
                studentId: studentId,
                classId: classId,
                state: REQUEST_STATE.APPROVED
            }
        });
    }

    async getById(classId) {
        const classEntity = await Class.findByPk(classId);

        if (classEntity == null) {
            throw new EntityNotFoundException('Class', classId);
        }

        return ClassDto.toDto(classEntity);
    }
}

export default new ClassService();
