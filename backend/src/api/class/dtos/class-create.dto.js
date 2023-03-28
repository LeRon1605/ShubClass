import Joi from 'joi';
class Dto {
    toEntity(dto) {
        return {
            id: dto.id,
            name: dto.name,
            description: dto.description,
            subjectName: dto.subjectName,
            numberOfStudent: dto.numberOfStudent,
            teacherId: dto.teacherId,
            createdAt: new Date(),
            updatedAt: new Date()
        };
    }

    toDto(entity) {
        return {};
    }
}

const ClassCreateDto = new Dto();
const ClassCreateScheme = Joi.object({
    id: Joi.string().min(4).max(8).required(),
    name: Joi.string().min(3).max(255).required(),
    description: Joi.string(),
    subjectName: Joi.string().min(3).max(255).required(),
    numberOfStudent: Joi.number().positive().greater(1).required()
});

export { ClassCreateDto, ClassCreateScheme };
