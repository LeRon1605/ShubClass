class Dto {
    toDto(entity) {
        return {
            id: entity.id,
            question: entity.question,
            answers: entity.answers.split('|')
        }
    }
}

const QuestionDto = new Dto();

export {
    QuestionDto
}