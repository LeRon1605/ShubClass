import { DateHelper } from '../../../shared/helpers/index.js';
class Dto {
    toEntity(dto) {
        return {
            examDetailId: dto.examDetailId,
            userAnswer: dto.answer,
            createdAt: DateHelper.getCurrentDate(),
            updatedAt: DateHelper.getCurrentDate()
        };
    }
}

const DoExamDto = new Dto();

export { DoExamDto };
