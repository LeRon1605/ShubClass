import Joi from 'joi';
import { randomUUID } from 'crypto';
import { ExamHelper } from '../../../shared/helpers/index.js';
import { EXAM_STATE } from '../../../shared/enum/index.js';

class Dto {
    toEntity(dto) {
        const id = randomUUID();
        return {
            exam: {
                id: id,
                name: dto.name,
                type: dto.type,
                state: EXAM_STATE.PENDING,
                classId: dto.classId,
                startTime: dto.startTime,
                endTime: dto.endTime,
                createAt: Date.now() + 7 * 3600 * 1000,
                updateAt: Date.now() + 7 * 3600 * 1000
            },
            details: ExamHelper.parseQuestionFromFile(dto.details, id)
        }
    }
};

const ExamCreateDto = new Dto();
const ExamCreateScheme = Joi.object({
    name: Joi.string().required(),
    type: Joi.string().required().valid('Exercise', 'Test'),
    classId: Joi.string().required(),
    startTime: Joi.date().greater(Date.now() + 7 * 3600 * 1000),
    endTime: Joi.date().greater(Joi.ref('startTime')),
    details: Joi.string().required()
});

export {
    ExamCreateDto,
    ExamCreateScheme
}