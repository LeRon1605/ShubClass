import Joi from 'joi';
import { randomUUID } from 'crypto';
import { ExamHelper } from '../../../shared/helpers/index.js';
import { EXAM_STATE } from '../../../shared/enum/index.js';

class Dto {
    toDto(entity) {
        return {
            id: entity.id,
            name: entity.name,
            type: entity.type,
            state: EXAM_STATE.PENDING,
            classId: entity.classId,
            startTime: entity.startTime,
            endTime: entity.endTime,
            createAt: entity.createdAt,
            updateAt: entity.updatedAt
        }
    }
};

const ExamDto = new Dto();

export {
    ExamDto
}