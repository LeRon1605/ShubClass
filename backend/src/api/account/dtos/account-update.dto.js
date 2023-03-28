import Joi from 'joi';

class Dto {
    toEntity(dto) {
        return {
            password: dto.password,
            isActivate: dto.isActivate,
            updateAt: new Date()
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
