'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class User extends Model {
    static associate({ Account, Class, StudentClass, UserExam }) {
      User.hasOne(Account, {
        foreignKey: 'id'
      });

      User.hasMany(Class, {
        foreignKey: 'teacherId'
      });

      User.hasMany(StudentClass, {
        foreignKey: 'studentId'
      });

      User.hasMany(UserExam, {
        foreignKey: 'studentId'
      });
    }
  }
  User.init({
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
    dateOfBirth: {
      type: DataTypes.DATE,
      allowNull: true,
      field: 'data_of_birth'
    },
    school: {
      type: DataTypes.STRING,
      allowNull: true,
      field: 'school'
    },
    grade: {
      type: DataTypes.INTEGER,
      allowNull: true,
      field: 'grade'
    },
    phoneNumber: {
      type: DataTypes.STRING,
      allowNull: true,
      field: 'phone_number'
    },
    address: {
      type: DataTypes.STRING,
      allowNull: true,
      field: 'address'
    },
    gender: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
      field: 'gender'
    },
    avatar: {
      type: DataTypes.STRING,
      allowNull: false,
      field: 'avatar'
    }
  }, {
    sequelize,
    modelName: 'User',
    tableName: 'users'
  });
  return User;
};