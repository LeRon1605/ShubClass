'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class UserAnswer extends Model {
    static associate({ ExamDetail, UserExam }) {
        UserAnswer.belongsTo(ExamDetail, {
            foreignKey: 'examDetailId'
        });

        UserAnswer.belongsTo(UserExam, {
            foreignKey: 'userExamId'
        });
    }
  }
  UserAnswer.init({
    userExamId: {
        type: DataTypes.STRING,
        allowNull: false,
        primaryKey: true,
        field: 'userExamId'
    },
    examDetailId: {
        type: DataTypes.STRING,
        allowNull: false,
        primaryKey: true,
        field: 'examDetailId'
    },
    userAnswer: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'user-answer'
    },
    state: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
        field: 'state'
    }
  }, {
    sequelize,
    modelName: 'UserAnswer',
    tableName: 'user-answers'
  });
  return UserAnswer;
};