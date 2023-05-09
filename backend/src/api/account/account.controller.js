import AccountService from './account.service.js';
import { AccountCreateDto, AccountUpdateDto } from './dtos/index.js';

class AccountController {
    async getUserOfAccount(req, res, next) {
        const data = await AccountService.getUserOfAccount(req.session.id);
        return res.status(200).json(data);
    }

    async getAllAccounts(req, res, next) {
        const data = await AccountService.getAllAccounts();
        return res.status(200).json(data);
    }

    async createAccount(req, res, next) {
        const entity = AccountCreateDto.toEntity(req.body);
        await AccountService.createAccount(entity, req.body.role);
        return res.status(201).json({
            message: 'Created account successfully.'
        });
    }
    async updateAccount(req, res, next) {
        const entity = AccountUpdateDto.toEntity({
            ...req.body
        });
        const result = await AccountService.updateAccount(
            req.params.id,
            entity
        );
        return res.status(200).json(result);
    }

    async deleteAccount(req, res, next) {
        await AccountService.deleteAccount(req.params.id);
        return res.status(200).json({
            message: 'Deleted account'
        });
    }

    async updateUser(req, res, next) {
        const result = await AccountService.updateUser(
            req.params.token,
            req.body
        );
        return res.status(200).json(result);
    }

    async login(req, res, next) {
        const { email, password } = req.body;
        const authCredential = await AccountService.login(email, password);
        return res.status(200).json(authCredential);
    }

    async activeAccount(req, res, next) {
        const { code, accountId } = req.query;
        await AccountService.activeAccount(code, accountId);
        return res.render('active_account_success');
    }

    async requestActiveMail(req, res, next) {
        const { email } = req.body;
        await AccountService.requestActiveMail(email);
        return res.status(200).json({
            message: 'Please check your mail to continue.'
        });
    }

    async requestForgetPassword(req, res, next) {
        const { email } = req.body;
        await AccountService.requestForgetPassword(email);
        return res.status(200).json({
            message: 'Please check your mail to continue'
        });
    }

    async getForgetPasswordPage(req, res, next) {
        const { token, accountId } = req.query;
        const accountDto = await AccountService.validateForgetPasswordToken(
            token,
            accountId
        );
        return res.render('forget_password', {
            title: 'Đổi mật khẩu',
            token: token,
            accountId: accountId
        });
    }

    async forgetPasswordHandler(req, res, next) {
        const { token, accountId, password } = req.body;
        await AccountService.forgetPassword(token, accountId, password);
        return res.render('forget_password_success');
    }

    async changePassword(req, res, next) {
        const accountId = req.session.id;
        const newPassword = req.body.newPassword;
        await AccountService.changePassword(accountId, newPassword);
        res.status(200).json({ message: 'Thay đổi mật khẩu thành công' });
    }

    async sayValid(req, res, next) {
        return res.status(200).json({
            message: 'Valid token'
        });
    }
}

export default new AccountController();
