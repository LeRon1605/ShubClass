'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class UserExam extends Model {
    static associate({ Exam, User, UserAnswer }) {
        UserExam.belongsTo(Exam, {
            foreignKey: 'examId'
        });

        UserExam.belongsTo(User, {
            foreignKey: 'studentId'
        });

        UserExam.hasMany(UserAnswer, {
            foreignKey: 'userExamId'
        });
    }
  }
  UserExam.init({
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
        field: 'id'
    },
    startAt: {
        type: DataTypes.DATE,
        allowNull: false,
        field: 'start-at'
    },
    endAt: {
        type: DataTypes.DATE,
        allowNull: true,
        field: 'end-at'
    },
    points: {
        type: DataTypes.INTEGER,
        allowNull: true,
        field: 'points'
    },
    examId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'examId'
    },
    studentId: {
        type: DataTypes.STRING,
        allowNull: false,
        field: 'studentId'
    }
  }, {
    sequelize,
    modelName: 'UserExam',
    tableName: 'user-exams'
  });
  return UserExam;
};