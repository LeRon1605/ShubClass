import sequelize from '../../database/models/index.cjs';
import { ANSWER_STATE, EXAM_STATE } from '../../shared/enum/index.js';
import {
    BadRequestException, EntityNotFoundException, ForbiddenException, NotFoundException
} from '../../shared/exceptions/index.js';
import { DateHelper } from '../../shared/helpers/index.js';

const { UserExam, Exam, ExamDetail, UserAnswer } = sequelize;
class UserExamService {
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
            throw new NotFoundException('You are not currently taking this exam.');
        }

        if (userExam.userId != currentSession.id) {
            throw new ForbiddenException('You are not allowed to submit another assigment.');
        }

        if (userExam.Exam.state != EXAM_STATE.PUBLISHED) {
            throw new BadRequestException('Invalid exam state, exam has already been closed or has not been opened yet');
        }

        if (userExam.endAt) {
            throw new BadRequestException('You have already submitted your submission.');
        }

        await UserExam.update({
            endAt: DateHelper.getCurrentDate(),
            points: (userExam.UserAnswers.filter(x => x.state == ANSWER_STATE.CORRECT).length * 1.0) / userExam.Exam.ExamDetails.length,
            updatedAt: DateHelper.getCurrentDate()
        }, {
            where: {
                id: id
            }
        });
        return userExam;
    }
}

export default new UserExamService();