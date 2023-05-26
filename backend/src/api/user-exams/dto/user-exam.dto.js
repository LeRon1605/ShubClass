class Dto {
    toDto(entity) {
        return {
            id: entity.id,
            userId: entity.userId,
            examId: entity.examId,
            startAt: entity.startAt,
            endAt: entity.endAt,
            userAnswers: entity.UserAnswers
                ? entity.UserAnswers.map((x) => {
                      return {
                          userExamId: x.userExamId,
                          examDetailId: x.examDetailId,
                          answer: x.userAnswer
                      };
                  })
                : []
        };
    }
}

const UserExamDto = new Dto();

export { UserExamDto };
