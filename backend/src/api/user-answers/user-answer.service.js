import sequelize from '../../database/models/index.cjs';
import {
    ForbiddenException,
    NotFoundException,
    BadRequestException,
    EntityNotFoundException
} from '../../shared/exceptions/index.js';
import { EXAM_STATE } from '../../shared/enum/index.js';
import { DateHelper } from '../../shared/helpers/index.js';

const { Class, ExamDetail, Exam, User, StudentClass, UserExam, UserAnswer } =
    sequelize;

class UserAnswerService {
    async createUserAnswer(entity, userExamId, currentSession) {
        const userExam = await UserExam.findOne({
            where: {
                id: userExamId
            },
            include: [Exam]
        });

        if (userExam == null) {
            throw new NotFoundException(
                'You are not currently taking this exam.'
            );
        }

        if (userExam.studentId != currentSession.id) {
            throw new ForbiddenException(
                `You are not allowed to do another's assigment.`
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

        const examDetail = await ExamDetail.findOne({
            where: {
                id: entity.examDetailId
            }
        });

        if (examDetail == null) {
            throw new EntityNotFoundException('Question', entity.examDetailId);
        }

        if (examDetail.examId != userExam.Exam.id) {
            throw new NotFoundException(
                `Question is not exist in exam with '${userExam.Exam.id}'`
            );
        }

        const userAnswer = await UserAnswer.findOne({
            where: {
                userExamId: userExamId,
                examDetailId: entity.examDetailId
            }
        });

        if (userAnswer == null) {
            entity.userExamId = userExamId;
            entity.state = examDetail.trueAnswer == entity.userAnswer;
            await UserAnswer.create(entity);
        } else {
            await UserAnswer.update(
                {
                    userAnswer: entity.userAnswer,
                    state: examDetail.trueAnswer == entity.userAnswer,
                    updatedAt: DateHelper.getCurrentDate()
                },
                {
                    where: {
                        userExamId: userExamId,
                        examDetailId: entity.examDetailId
                    }
                }
            );
        }
    }
}

export default new UserAnswerService();
