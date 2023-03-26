import sequelize from "../../database/models/index.cjs";
import { AccountDto } from "./dtos/account.dto.js";
import {
  EntityNotFoundException,
  EntityAlreadyExistException,
  EntityForbiddenUpdateException,
  EntityForbiddenDeleteException,
} from "../../exceptions/index.js";

const { User, Account, Role } = sequelize;

class AccountService {
  async getUserOfAccount(accountId) {
    const user = await User.findAll({
      where: {
        userId: accountId,
      },
    });
    return user.map((x) => AccountDto.toDto(x));
  }

  async getAllAccountsOfRole(roleId) {
    const role = await Role.findByPk(roleId);
    if (role == null) {
      throw new EntityNotFoundException("Role", roleId);
    }
    const data = await Account.findAll({
      where: {
        accountId: roleId,
      },
    });
    return data.map((x) => AccountDto.toDto(x));
  }

  async createAccount(newAccount) {
    const accountEntity = await Account.findByPk(newAccount.id);
    if (accountEntity != null) {
      throw new EntityAlreadyExistException("Account", accountEntity.id);
    }

    const role = await Role.findByPk(newAccount.roleId);
    if (role == null) {
      throw new EntityNotFoundException("Role", newAccount.roleId);
    }
    return await Account.create(newAccount);
  }

  async updateAccount(id, newAccount) {
    const accountEntity = await Account.findByPk(id);
    if (accountEntity == null) {
      throw new EntityNotFoundException("Account", id);
    }

    const role = await Role.findByPk(newAccount.roleId);
    if (role == null) {
      throw new EntityNotFoundException("Role", newAccount.roleId);
    }
    if (role.roleId != newAccount.roleId) {
      throw new EntityForbiddenUpdateException("Account", accountEntity.id);
    }

    await accountEntity.update(
      { ...accountEntity, ...newAccount },
      {
        where: {
          id: id,
        },
      }
    );

    const dto = AccountDto.toDto(await Account.findByPk(id));
    return dto;
  }

  async deleteAccount(id, roleId) {
    const accountEntity = await Account.findByPk(id);
    if (accountEntity == null) {
      throw new EntityNotFoundException("Account", id);
    }
    if (accountEntity.roleId != roleId) {
      throw new EntityForbiddenDeleteException("Account", id);
    }
    await Account.destroy({
      where: {
        id: id,
      },
    });
  }
}

export default new AccountService();
