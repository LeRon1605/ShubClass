import sequelize from '../../database/models/index.cjs';
import { AccountDto } from './dtos/account.dto.js';
import {
    EntityNotFoundException,
    EntityAlreadyExistException,
    EntityForbiddenUpdateException,
    EntityForbiddenDeleteException,
    BadRequestException,
    NotFoundException
} from '../../shared/exceptions/index.js';
import { MailService } from '../../services/index.js';
import TokenProvider from './token.provider.js';
import { randomUUID } from 'crypto';
import { CacheService } from '../../services/index.js';
import { ACCOUNT_STATE } from '../../shared/enum/index.js';

const { User, Account, Role, StudentClass } = sequelize;

class AccountService {
    async getUserOfAccount(accountId) {
        const user = await User.findOne({
            where: { userId: accountId }
        });
        if (!user) {
            throw new EntityNotFoundException('User', accountId);
        }
        return AccountDto.toDto(user);
    }

    async getAllAccounts() {
        const data = await Account.findAll();
        return data.map((x) => AccountDto.toDto(x));
    }

    async createAccount(newAccount, roleName) {
        const accountEntity = await Account.findOne({
            where: {
                email: newAccount.email
            }
        });

        if (accountEntity != null) {
            throw new EntityAlreadyExistException(
                'Account',
                newAccount.email,
                // @ts-ignore
                'email'
            );
        }

        const role = await Role.findOne({
            where: {
                name: roleName
            }
        });

        if (role == null) {
            // @ts-ignore
            throw new EntityNotFoundException('Role', roleName, 'name');
        }

        newAccount.roleId = role.id;
        await Account.create(newAccount, {
            include: [User]
        });

        const activeToken = randomUUID();
        const mailContent = `
        <div style="padding: 10px; background-color: #003375">
            <div style="padding: 10px; background-color: white;">
                <h4 style="color: #0085ff">Xin chào ${newAccount.User.name}, cảm ơn bạn đã chọn chúng tôi!!</h4>
                <p style="color: black">Để tiếp tục sử dụng ứng dụng, vui lòng nhấn vào <a href="${process.env.SERVER}/api/accounts/active?code=${activeToken}&accountId=${newAccount.id}">đây</a> để kích hoạt tài khoản.</p>
                <p><b style="color:red">Chú ý:</b> Mã kích hoạt có hạn trong vòng 3 ngày.</p>
            </div>
        </div>
    `;
        await CacheService.set(
            `active_code_${newAccount.id}`,
            { token: activeToken, accountId: newAccount.id },
            3 * 60 * 60
        );
        MailService.sendMail(
            newAccount.email,
            'Kích hoạt tài khoản ShubClass',
            mailContent
        );
    }

    async updateAccount(id, newAccount) {
        const accountEntity = await Account.findByPk(id);
        if (accountEntity == null) {
            throw new EntityNotFoundException('Account', id);
        }

        const role = await Role.findByPk(newAccount.roleId);
        if (role == null) {
            throw new EntityNotFoundException('Role', newAccount.roleId);
        }
        if (role.roleId != newAccount.roleId) {
            throw new EntityForbiddenUpdateException(
                'Account',
                accountEntity.id
            );
        }

        await accountEntity.update(
            { ...accountEntity, ...newAccount },
            {
                where: {
                    id: id
                }
            }
        );

        const dto = AccountDto.toDto(await Account.findByPk(id));
        return dto;
    }

    async deleteAccount(id, roleId) {
        const accountEntity = await Account.findByPk(id);
        if (accountEntity == null) {
            throw new EntityNotFoundException('Account', id);
        }
        if (accountEntity.roleId != roleId) {
            throw new EntityForbiddenDeleteException('Account', id);
        }
        await Account.destroy({
            where: {
                id: id
            }
        });
    }

    async login(email, password) {
        const account = await Account.findOne({
            where: {
                email: email,
                password: password
            },
            include: [User, Role]
        });

        if (account == null) {
            throw new NotFoundException('Email or password is incorrect.');
        }

        const authCredential = TokenProvider.generateAuthCredential(account);
        return authCredential;
    }

    async activeAccount(code, accountId) {
        const account = await Account.findByPk(accountId);
        if (account == null) {
            throw new EntityNotFoundException('Account', accountId);
        }

        const savedRecord = await CacheService.get(`active_code_${accountId}`);
        if (savedRecord != null) {
            if (savedRecord.token == code) {
                await Account.update(
                    { state: ACCOUNT_STATE.ACTIVE },
                    {
                        where: {
                            id: account.id
                        }
                    }
                );
                await CacheService.remove(`active_code_${accountId}`);
            } else {
                throw new BadRequestException('Invalid token');
            }
        } else {
            throw new BadRequestException(
                'Token has already expires, please request to send a new email.'
            );
        }
    }

    async requestActiveMail(email) {
        const account = await Account.findOne({
            where: {
                email: email
            },
            include: User
        });
        if (account == null) {
            // @ts-ignore
            throw new EntityNotFoundException('Account', email, 'email');
        }

        if (account.state == ACCOUNT_STATE.ACTIVE) {
            throw new BadRequestException(
                'Account has already been activated.'
            );
        }

        const savedRecord = await CacheService.get(`active_code_${account.id}`);
        if (savedRecord != null) {
            throw new BadRequestException(
                'Active request is valid for 3 days, please check your mail for continue.'
            );
        }

        const activeToken = randomUUID();
        const mailContent = `
            <div style="padding: 10px; background-color: #003375">
                <div style="padding: 10px; background-color: white;">
                    <h4 style="color: #0085ff">Xin chào ${account.User.name}, cảm ơn bạn đã chọn chúng tôi!!</h4>
                    <p style="color: black">Để tiếp tục sử dụng ứng dụng, vui lòng nhấn vào <a href="${process.env.SERVER}/api/accounts/active?code=${activeToken}&accountId=${account.id}">đây</a> để kích hoạt tài khoản.</p>
                    <p><b style="color:red">Chú ý:</b> Mã kích hoạt có hạn trong vòng 3 ngày.</p>
                </div>
            </div>
        `;

        await CacheService.set(
            `active_code_${account.id}`,
            { token: activeToken, accountId: account.id },
            3 * 60 * 60
        );
        MailService.sendMail(
            account.email,
            'Kích hoạt tài khoản ShubClass',
            mailContent
        );
    }

    async changePassword(accountId, newPassword) {
        const account = await Account.findByPk(accountId);
        if (!account) {
            throw new EntityNotFoundException('Account', accountId);
        }

        account.password = newPassword;
        await account.save();
    }

    async requestForgetPassword(email) {
        const account = await Account.findOne({
            where: {
                email: email
            },
            include: User
        });

        if (account == null) {
            throw new EntityNotFoundException('Account', email, 'email');
        }

        const savedRecord = await CacheService.get(
            `request_change_password_token_${account.id}`
        );
        if (savedRecord != null) {
            throw new BadRequestException(
                'Forgetpassword request is valid for 3 days, please check your mail for continue.'
            );
        }

        const forgetPasswordToken = randomUUID();
        const mailContent = `
            <div style="padding: 10px; background-color: #003375">
                <div style="padding: 10px; background-color: white;">
                    <h4 style="color: #0085ff">Xin chào ${account.User.name}.!!</h4>
                    <p style="color: black">Để thay đổi mật khẩu, vui lòng nhấn vào <a href="${process.env.SERVER}/api/accounts/forget-password/callback?token=${forgetPasswordToken}&accountId=${account.id}">đây</a> để đổi mật khẩu tài khoản.</p>
                    <p><b style="color:red">Chú ý:</b> Thư có giá trị trong vòng 3 ngày.</p>
                </div>
            </div>
        `;

        await CacheService.set(
            `request_change_password_token_${account.id}`,
            { token: forgetPasswordToken, accountId: account.id },
            3 * 60 * 60
        );
        MailService.sendMail(
            account.email,
            'Thay đổi mật khẩu tài khoản ShubClass',
            mailContent
        );
    }

    async forgetPassword(token, accountId, password) {
        const account = await Account.findByPk(accountId);
        if (account == null) {
            throw new EntityNotFoundException('Account', accountId);
        }

        const savedRecord = await CacheService.get(
            `request_change_password_token_${accountId}`
        );
        if (savedRecord != null) {
            if (savedRecord.token == token) {
                await Account.update(
                    { password: password },
                    {
                        where: {
                            id: account.id
                        }
                    }
                );
                await CacheService.remove(
                    `request_change_password_token_${accountId}`
                );
            } else {
                throw new BadRequestException('Invalid token');
            }
        } else {
            throw new BadRequestException(
                'Token has already expires, please request to send a new email.'
            );
        }
    }

    async validateForgetPasswordToken(token, accountId) {
        const account = await Account.findByPk(accountId);
        if (account == null) {
            throw new EntityNotFoundException('Account', accountId);
        }

        const savedRecord = await CacheService.get(
            `request_change_password_token_${accountId}`
        );
        if (savedRecord == null) {
            throw new BadRequestException(
                'Token has already expires, please request to send a new email.'
            );
        } else if (savedRecord.token != token) {
            throw new BadRequestException('Invalid token.');
        }

        return AccountDto.toDto(account);
    }
}

export default new AccountService();
