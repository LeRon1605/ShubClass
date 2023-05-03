import sequelize from '../../database/models/index.cjs';
import { ANSWER_STATE, EXAM_STATE, EXAM_TYPE } from '../../shared/enum/index.js';
import {
    BadRequestException,
    EntityNotFoundException,
    ForbiddenException,
    NotFoundException,
    EntityForbiddenAccessException
} from '../../shared/exceptions/index.js';
import { DateHelper } from '../../shared/helpers/index.js';
import { UserExamCreateDto } from './dto/user-exam-create.dto.js';
import { UserExamDto } from './dto/user-exam.dto.js';

const { UserExam, Exam, ExamDetail, UserAnswer, Class, StudentClass } = sequelize;
class UserExamService {
    async startDoingExam(examId, currentSession) {
        const exam = await Exam.findOne({
            where: {
                id: examId
            },
            include: [
                {
                    model: Class,
                    include: [StudentClass]
                }
            ]
        });

        if (exam == null) {
            throw new EntityNotFoundException('Exam', examId);
        }

        if (exam.state != EXAM_STATE.PUBLISHED) {
            throw new BadRequestException('Invalid exam state, exam has already been closed or has not been opened yet');
        }

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

        const userExam = await UserExam.findOne({
            where: {
                studentId: currentSession.id,
                examId: examId
            },
            include: [
                {
                    model: UserAnswer,
                    include: [ExamDetail]
                }
            ]
        });

        if (exam.type == EXAM_TYPE.TEST) {
            if (userExam) return UserExamDto.toDto(userExam);
        } else {
            if (userExam && userExam.endAt == null) {
                return UserExamDto.toDto(userExam);
            }
        }

        return UserExamDto.toDto(await UserExam.create(UserExamCreateDto.toEntity(currentSession.id, examId)));
    }

    async submitExam(id, currentSession) {
        const userExam = await UserExam.findOne({
            where: {
                id: id
            },
            include: [
                {
                    model: Exam,
                    include: [ExamDetail]
                },
                UserAnswer
            ]
        });

        if (userExam == null) {
            throw new NotFoundException(
                'You are not currently taking this exam.'
            );
        }

        if (userExam.studentId != currentSession.id) {
            throw new ForbiddenException(
                'You are not allowed to submit another assigment.'
            );
        }

        if (userExam.Exam.state != EXAM_STATE.PUBLISHED) {
            throw new BadRequestException(
                'Invalid exam state, exam has already been closed or has not been opened yet'
            );
        }

        if (userExam.endAt) {
            throw new BadRequestException(
                'You have already submitted your submission.'
            );
        }

        await UserExam.update(
            {
                endAt: DateHelper.getCurrentDate(),
                points:
                    (userExam.UserAnswers.filter(
                        (x) => x.state == ANSWER_STATE.CORRECT
                    ).length *
                        10.0) /
                    userExam.Exam.ExamDetails.length,
                updatedAt: DateHelper.getCurrentDate()
            },
            {
                where: {
                    id: id
                }
            }
        );
    }
}

export default new UserExamService();
