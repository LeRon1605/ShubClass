'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Account extends Model {
    static associate({ User, Role }) {
      Account.hasOne(User, {
        foreignKey: 'id'
      });
      Account.belongsTo(Role, {
        foreignKey: 'roleId'
      });
    }
  }
  Account.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'id'
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'email'
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'password'
    },
    state: {
        type: DataTypes.INTEGER,
        allowNull: false,
        field: 'state'
    },
    roleId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'roleId'
    }
  }, {
    sequelize,
    modelName: 'Account',
    tableName: 'accounts'
  });
  return Account;
};