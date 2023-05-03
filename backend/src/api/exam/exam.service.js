import schedule from 'node-schedule';
import moment from 'moment';
import {
    BadRequestException,
    EntityForbiddenAccessException,
    EntityNotFoundException,
    ForbiddenException,
    NotFoundException
} from '../../shared/exceptions/index.js';
import { Op } from 'sequelize';
import sequelize from '../../database/models/index.cjs';
import { EXAM_STATE, EXAM_TYPE } from '../../shared/enum/index.js';
import { ExamDto, QuestionDto, ExamResultDto } from './dto/index.js';
const { Class, ExamDetail, Exam, User, StudentClass, UserExam, UserAnswer } =
    sequelize;

class ExamService {
    async create(body, teacherId) {
        const { exam, details } = body;

        const classEntity = await Class.findByPk(exam.classId);
        if (classEntity == null) {
            throw new EntityNotFoundException('Class', exam.classId);
        }

        if (classEntity.teacherId != teacherId) {
            throw new EntityForbiddenAccessException('Class', exam.classId);
        }

        if (exam.type == EXAM_TYPE.TEST) {
            const duplicate = await Exam.findOne({
                where: {
                    [Op.and]: {
                        startTime: {
                            [Op.and]: {
                                [Op.gte]: exam.startTime,
                                [Op.lte]: exam.endTime
                            }
                        },
                        endTime: {
                            [Op.and]: {
                                [Op.gte]: exam.startTime,
                                [Op.lte]: exam.endTime
                            }
                        }
                    }
                }
            });
    
            if (duplicate != null) {
                throw new BadRequestException(
                    'Invalid exam duration time, there is existed exam in that duration'
                );
            }
        }

        await Exam.create(exam);
        await ExamDetail.bulkCreate(details);

        const fireDate = moment(exam.startTime, 'YYYY-MM-DDTHH:mm:ssZ')
            .add('hours', -7)
            .toDate();
        const endExamFireTime = moment(exam.endTime, 'YYYY-MM-DDTHH:mm:ssZ')
            .add('hours', -7)
            .toDate();

        schedule.scheduleJob(`exam_start_${exam.id}`, fireDate, async () => {
            await Exam.update(
                { state: EXAM_STATE.PUBLISHED },
                {
                    where: {
                        id: exam.id
                    }
                }
            );

            schedule.scheduleJob(
                `exam_end_${exam.id}`,
                endExamFireTime,
                async () => {
                    await Exam.update(
                        { state: EXAM_STATE.CLOSED },
                        {
                            where: {
                                id: exam.id
                            }
                        }
                    );
                }
            );
        });

        return exam;
    }

    async remove(id, teacherId) {
        const exam = await Exam.findOne({
            where: {
                id
            },
            include: Class
        });
        if (exam == null) {
            throw new EntityNotFoundException('Exam', id);
        }

        if (exam.Class.teacherId != teacherId) {
            throw new EntityForbiddenAccessException('Class', exam.Class.id);
        }

        if (exam.state != EXAM_STATE.PENDING) {
            throw new BadRequestException(
                'Invalid action, can not delete processed exam.'
            );
        }

        await Exam.destroy({
            where: {
                id
            }
        });

        schedule.scheduledJobs[`exam_start_${exam.id}`]?.cancel();
    }

    async getAllExamInClass(classId, currentSession) {
        const classEntity = await Class.findOne({
            where: {
                id: classId
            },
            include: [Exam, StudentClass]
        });

        if (classEntity == null) {
            throw new EntityNotFoundException('Class', classId);
        }

        if (currentSession.role == 'Teacher') {
            if (currentSession.id != classEntity.teacherId) {
                throw new EntityForbiddenAccessException('Class', classId);
            }
        } else {
            if (
                !classEntity.StudentClasses.some(
                    (x) => x.studentId == currentSession.id
                )
            ) {
                throw new EntityForbiddenAccessException('Class', classId);
            }
        }

        return classEntity.Exams.map((x) =>
            ExamDto.toDto(x)
        );
    }

    async getQuestion(id, currentSession) {
        const exam = await Exam.findOne({
            where: {
                id: id
            },
            include: [
                ExamDetail,
                {
                    model: Class,
                    include: [StudentClass]
                }
            ]
        });

        if (exam == null) {
            throw new EntityNotFoundException('Exam', id);
        }

        if (currentSession.role == 'Teacher') {
            if (currentSession.id != exam.Class.teacherId) {
                throw new EntityForbiddenAccessException(
                    'Class',
                    exam.Class.id
                );
            }

            return exam.ExamDetails.map((x) => QuestionDto.toDto(x));
        } else {
            if (
                !exam.Class.StudentClasses.some(
                    (x) => x.studentId == currentSession.id
                )
            ) {
                throw new EntityForbiddenAccessException(
                    'Class',
                    exam.Class.id
                );
            }

            const latestTakenExam = await UserExam.findOne({
                where: {
                    examId: exam.id
                },
                order: [['createdAt', 'DESC']]
            });

            if (!latestTakenExam || latestTakenExam.endTime) {
                throw new ForbiddenException(
                    `You are not currently taking exam with id '${exam.id}'`
                );
            }

            return exam.ExamDetails.map((x) => QuestionDto.toDto(x));
        }
    }

    async getResult(id, currentSession) {
        const exam = await Exam.findOne({
            where: {
                id: id
            },
            include: [
                {
                    model: Class,
                    include: [StudentClass]
                }
            ]
        });
        if (exam == null) {
            throw new EntityNotFoundException('Exam', id);
        }

        if (currentSession.role == 'Teacher') {
            if (currentSession.id != exam.Class.teacherId) {
                throw new EntityForbiddenAccessException(
                    'Class',
                    exam.Class.id
                );
            }
        } else {
            if (
                !exam.Class.StudentClasses.some(
                    (x) => x.studentId == currentSession.id
                )
            ) {
                throw new EntityForbiddenAccessException(
                    'Class',
                    exam.Class.id
                );
            }
        }

        const userExams = await UserExam.findAll({
            where: {
                examId: id,
                endAt: {
                    [Op.ne]: null
                }
            },
            include: [User, Exam]
        });

        return userExams.map((x) => ExamResultDto.toDto(x));
    }

    async getStudentResultInExam(examId, userId) {
        const userExams = await UserExam.findAll({
            where: {    
                examId: examId,
                studentId: userId
            },
            include: [User, Exam]
        });

        if (!userExams) {
            throw NotFoundException('Student did not take this exam.');
        }

        return userExams.map(x => ExamResultDto.toDto(x));
    }
}

export default new ExamService();
