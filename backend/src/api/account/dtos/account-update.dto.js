import Joi from 'joi';
import { DEFAULT_AVATAR } from '../../../shared/enum/index.js';

class Dto {
    toEntity(dto) {
        return {
            name: dto.name,
            phoneNumber: dto.phoneNumber,
            dateOfBirth: dto.dateOfBirth,
            school: dto.school,
            grade: dto.grade,
            address: dto.address,
            gender: dto.gender,
            avatar: dto.gender ? DEFAULT_AVATAR.MAN : DEFAULT_AVATAR.WOMAN,
            updateAt: new Date()
        };
    }

    toDto(entity) {
        return {};
    }
}

const AccountUpdateDto = new Dto();
const AccountUpdateScheme = Joi.object({
    name: Joi.string(),
    phoneNumber: Joi.string(),
    address: Joi.string(),
    grade: Joi.number().positive(),
    gender: Joi.boolean(),
    school: Joi.string(),
    dateOfBirth: Joi.date()
});

export { AccountUpdateDto, AccountUpdateScheme };
