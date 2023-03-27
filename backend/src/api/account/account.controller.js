import AccountService from "./account.service.js";
import { AccountCreateDto, AccountUpdateDto } from "./dtos/index.js";

class AccountController {
  async getUserOfAccount(req, res, next) {
    try {
      let data = null;
      data = await AccountService.getUserOfAccount(req.session.id);
      return res.status(200).json(data);
    } catch (error) {
      next(error);
    }
  }

  // doesn't need
  async getAllAccountsOfRole(req, res, next) {
    try {
      let data = null;
      data = await AccountService.getAllAccountsOfRole(req.session.id);
      return res.status(200).json(data);
    } catch (error) {
      next(error);
    }
  }

  async createAccount(req, res, next) {
    try {
      const entity = AccountCreateDto.toEntity(req.body);
      await AccountService.createAccount(entity, req.body.role);
      return res.status(201).json({
        message: 'Created account successfully.'
      })
    } catch (error) {
      next(error);
    }
  }
  async updateAccount(req, res, next) {
    try {
      const entity = AccountUpdateDto.toEntity({
        ...req.body,
      });
      const result = await AccountService.updateAccount(req.params.id, entity);
      return res.status(200).json(result);
    } catch (error) {
      next(error);
    }
  }

  async deleteAccount(req, res, next) {
    try {
      await AccountService.deleteAccount(req.params.id);
      return res.status(200).json;
    } catch (error) {
      next(error);
    }
  }

  async login(req, res, next) {
    try {
      const { email, password } = req.body;
      const authCredential = await AccountService.login(email, password);
      return res.status(200).json(authCredential);
    } catch (error) {
      next(error);
    }
  }

  async activeAccount(req, res, next) {
    try {
      const { code, accountId } = req.query;
      await AccountService.activeAccount(code, accountId);
      return res.send('Kích hoạt tài khoản thành công');
    } catch (error) {
      next(error);
    }
  }

  async requestActiveMail(req, res, next) {
    try {
      const { id } = req.body;
      await AccountService.requestActiveMail(id);
      return res.status(200);
    } catch (error) {
      next(error);
    }
  }
}

export default new AccountController();
