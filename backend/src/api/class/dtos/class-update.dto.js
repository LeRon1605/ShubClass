import Joi from 'joi';
class Dto {
    toEntity(dto) {
        return {
            name: dto.name,
            description: dto.description,
            subjectName: dto.subjectName,
            numberOfStudent: dto.numberOfStudent,
            teacherId: dto.teacherId,
            updatedAt: new Date()
        }
    }

    toDto(entity) {
        return {
            
        }
    }
};

const ClassUpdateDto = new Dto();
const ClassUpdateScheme = Joi.object({
    name: Joi.string().min(3).max(255).required(),
    description: Joi.string(),
    subjectName: Joi.string().min(3).max(255).required(),
    numberOfStudent: Joi.number().positive().greater(1).required()
});

export {
    ClassUpdateDto,
    ClassUpdateScheme
}