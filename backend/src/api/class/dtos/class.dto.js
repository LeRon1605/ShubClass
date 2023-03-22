class Dto {
    toEntity(dto) {
        return {
            username: dto.username
        }
    }

    toDto(entity) {
        return {
            id: entity.id,
            name: entity.name,
            description: entity.description,
            subjectName: entity.subjectName,
            numberOfStudent: entity.numberOfStudent,
            teacherId: entity.teacherId,
            createdAt: entity.createdAt,
            updatedAt: entity.updatedAt
        }
    }
};

const ClassDto = new Dto();

export {
    ClassDto
}