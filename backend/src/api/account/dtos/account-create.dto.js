import Joi from "joi";

class Dto {
  toEntity(dto) {
    return {
      id: dto.id,
      email: dto.email,
      password: dto.password,
      isActivate: dto.isActivate,
      roleId: dto.roleId,
      createAt: new Date(),
      updateAt: new Date(),
    };
  }

  toDto(entity) {
    return {};
  }
}

const AccountCreateDto = new Dto();
const AccountCreateScheme = Joi.object({
  id: Joi.string().min(4).max(8).required(),
  email: Joi.string().email().required(),
  password: Joi.string()
    .pattern(
      new RegExp(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})"
      )
    )
    .required(),
  isActivated: Joi.bool().required,
});

export { AccountCreateDto, AccountCreateScheme };
