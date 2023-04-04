class Dto {
    toEntity(dto) {
        return {};
    }

    toDto(entity) {
        return {
            id: entity.id,
            email: entity.email,
            password: entity.password,
            name: entity.name,
            phoneNumber: entity.phoneNumber,
            dateOfBirth: entity.dateOfBirth,
            school: entity.school,
            grade: entity.grade,
            address: entity.address,
            gender: entity.gender,
            avatar: entity.avatar,
            createAt: new Date(),
            updateAt: new Date()
        };
    }
}

const StudentDto = new Dto();

export { StudentDto };
