import Joi from 'joi';
import { DEFAULT_AVATAR } from '../../../shared/enum/index.js';

class Dto {
    toEntity(dto) {
        return {
            password: dto.password,
            updateAt: new Date(),
            user: {
                name: dto.name,
                phoneNumber: dto.phoneNumber,
                dateOfBirth: dto.dateOfBirth,
                school: dto.school,
                grade: dto.grade,
                address: dto.address,
                gender: dto.gender,
                avatar: dto.gender ? DEFAULT_AVATAR.MAN : DEFAULT_AVATAR.WOMAN
            }
        };
    }

    toDto(entity) {
        return {};
    }
}

const AccountUpdateDto = new Dto();
const AccountUpdateScheme = Joi.object({
    password: Joi.string()
        .pattern(
            new RegExp(
                '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})'
            )
        )
        .required(),
    isActivated: Joi.bool().required
});

export { AccountUpdateDto, AccountUpdateScheme };
