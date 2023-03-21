'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Role extends Model {
    static associate({ Account }) {
        Role.hasMany(Account);
    }
  }
  Role.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'id'
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'name'
    },
    description: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'description'
    }
  }, {
    sequelize,
    modelName: 'Role',
    tableName: 'roles'
  });
  return Role;
};