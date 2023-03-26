import { ForbiddenException } from "../../exceptions/index.js";
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
      if (req.session.roleId) {
        const entity = AccountCreateDto.toEntity({
          ...req.body,
          roleId: req.session.roleId,
        });
        const result = await AccountService.createAccount(entity);
        return res.status(201).json(result);
      }
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
}

export default new AccountController();
