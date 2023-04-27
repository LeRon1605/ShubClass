import { DoExamDto } from './dto/index.js';
import UserAnswerService from './user-answer.service.js';
class UserAnswerController {
    async doExam(req, res, next) {
        const entity = DoExamDto.toEntity(req.body);
        await UserAnswerService.createUserAnswer(entity, req.params.id, req.session);
        return res.status(200).json({
            message: 'Saved answer'
        });
    }
}

export default new UserAnswerController();