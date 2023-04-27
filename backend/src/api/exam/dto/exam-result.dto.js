import { ANSWER_STATE } from '../../../shared/enum/index.js';

class Dto {
    toDto(entity) {
        return {
            exam: {
                id: entity.Exam.id,
                name: entity.Exam.name,
                startTime: entity.Exam.startTime,
                endTime: entity.Exam.endTime
            },
            user: {
                id: entity.User.id,
                name: entity.User.name,
                avatar: entity.User.avatar
            },
            point: entity.points,
            startAt: entity.startAt,
            endAt: entity.endAt 
        }
    }
}

const ExamResultDto = new Dto();

export {
    ExamResultDto
}