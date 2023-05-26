import { randomUUID } from 'crypto';
import { DateHelper } from '../../../shared/helpers/index.js';

class Dto {
    toEntity(studentId, examId) {
        return {
            id: randomUUID(),
            studentId: studentId,
            examId: examId,
            startAt: DateHelper.getCurrentDate(),
            endAt: null
        };
    }
}

const UserExamCreateDto = new Dto();

export { UserExamCreateDto };
