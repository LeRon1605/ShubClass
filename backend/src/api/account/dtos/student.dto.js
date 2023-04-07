class Dto {
    toEntity(dto) {
        return {};
    }

    toDto(entity) {
        return {
            id: entity.id,
            name: entity.name,
            phoneNumber: entity.phoneNumber,
            gender: entity.gender,
            avatar: entity.avatar
        };
    }
}

const StudentDto = new Dto();

export { StudentDto };
