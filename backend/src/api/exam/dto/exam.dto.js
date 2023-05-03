class Dto {
    toDto(entity) {
        return {
            id: entity.id,
            name: entity.name,
            type: entity.type,
            state: entity.state,
            classId: entity.classId,
            startTime: entity.startTime,
            endTime: entity.endTime,
            createAt: entity.createdAt,
            updateAt: entity.updatedAt
        };
    }
}

const ExamDto = new Dto();

export { ExamDto };
