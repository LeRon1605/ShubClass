import Joi from 'joi';
import { randomUUID } from 'crypto';
import { ACCOUNT_STATE, DEFAULT_AVATAR } from '../../../shared/enum/index.js';

class Dto {
    toEntity(dto) {
        const id = randomUUID();
        return {
            id: id,
            email: dto.email,
            password: dto.password,
            state: ACCOUNT_STATE.PENDING,
            User: {
                id: id,
                name: dto.name,
                phoneNumber: dto.phoneNumber,
                address: dto.address,
                grade: dto.grade,
                gender: dto.gender,
                school: dto.school,
                dateOfBirth: dto.dateOfBirth,
                avatar: dto.gender ? DEFAULT_AVATAR.MAN : DEFAULT_AVATAR.WOMAN
            },
            createAt: new Date(),
            updateAt: new Date()
        };
    }

    toDto(entity) {
        return {};
    }
}

const AccountCreateDto = new Dto();
const AccountCreateScheme = Joi.object({
    email: Joi.string().email().required(),
    password: Joi.string().required(),
    name: Joi.string().required(),
    phoneNumber: Joi.string(),
    address: Joi.string(),
    grade: Joi.number().positive(),
    gender: Joi.boolean(),
    school: Joi.string(),
    dateOfBirth: Joi.date(),
    role: Joi.string().required().valid('Teacher', 'Student')
});

export { AccountCreateDto, AccountCreateScheme };
