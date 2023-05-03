import UserExamService from './user-exam.service.js';

class UserExamController {
    async submit(req, res, next) {
        const result = await UserExamService.submitExam(
            req.params.id,
            req.session
        );
        return res.status(200).json(result);
    }
}

export default new UserExamController();
