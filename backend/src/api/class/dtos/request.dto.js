import { StudentDto } from '../../account/dtos/student.dto.js';

class Dto {
    toEntity(dto) {
        return {};
    }

    toDto(entity) {
        return {
            state: entity.state,
            student: StudentDto.toDto(entity.User)
        };
    }
}

const RequestDto = new Dto();

export { RequestDto };
