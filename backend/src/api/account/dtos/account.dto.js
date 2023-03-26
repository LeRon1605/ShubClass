class Dto {
  toEntity(dto) {
    return {};
  }

  toDto(entity) {
    return {
      id: entity.id,
      email: entity.email,
      password: entity.password,
      isActivate: entity.isActivate,
      roleId: entity.roleId,
      createAt: new Date(),
      updateAt: new Date(),
    };
  }
}

const AccountDto = new Dto();

export { AccountDto };
