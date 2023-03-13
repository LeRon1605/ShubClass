import Joi from 'joi';

class Dto {
    toEntity(dto) {
        return {
            username: dto.username
        }
    }

    toDto(entity) {
        return {
            username: entity.username
        }
    }
}

const HomeCreateDto = new Dto()

const HomeCreateScheme = Joi.object({
    username: Joi.string()
                 .alphanum()
                 .min(3)
                 .max(30)
                 .required()
});

export {
    HomeCreateDto,
    HomeCreateScheme
}